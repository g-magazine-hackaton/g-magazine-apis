package com.rocketsorry.gmagazine.persistence.repository.impl

import com.rocketsorry.gmagazine.persistence.ESQueryBuilder
import com.rocketsorry.gmagazine.persistence.doc.ConsumerDoc
import com.rocketsorry.gmagazine.persistence.enum.IdField
import com.rocketsorry.gmagazine.persistence.repository.CommonESQueryRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.UpdateResponse
import org.springframework.stereotype.Repository

@Repository
class ConsumerRepository(
    override var operations: ElasticsearchOperations,
    override var esQueryBuilder: ESQueryBuilder

) : CommonESQueryRepository<ConsumerDoc> {
    override fun indexName() = "consumers"
    override fun docClassType() = ConsumerDoc::class.java
    override fun idFieldType(): IdField = IdField.CONSUMER

    fun updateLikeList(
        docId: String,
        magazinId: String,
        isPlus: Boolean
    ): UpdateResponse {
        val script = if (isPlus) {
            "ctx._source.liked_magazine_ids.add(params.new_magazine_id)"
        } else {
            "ctx._source.liked_magazine_ids.removeAll(Collections.singleton(params.new_magazine_id))"
        }
        val param = mapOf("new_magazine_id" to magazinId)

        return updateWithScript(docId, script, param)
    }

    fun updateFollowingList(
        consumerId: String,
        myId: String,
        isFollow: Boolean
    ): UpdateResponse {
        val script = if (isFollow) {
            "ctx._source.following_consumer_ids.add(params.new_consumer_id)"
        } else {
            "ctx._source.following_consumer_ids.removeAll(Collections.singleton(params.new_consumer_id))"
        }
        val param = mapOf("new_consumer_id" to consumerId)

        return updateWithScript(myId, script, param)
    }

    fun updateFollowerList(
        consumerId: String,
        myId: String,
        isFollow: Boolean
    ): UpdateResponse {

        val script = if (isFollow) {
            "ctx._source.follower_consumer_ids.add(params.new_consumer_id)"
        } else {
            "ctx._source.follower_consumer_ids.removeAll(Collections.singleton(params.new_consumer_id))"
        }
        val param = mapOf("new_consumer_id" to myId)

        return updateWithScript(consumerId, script, param)
    }

    fun updateScrappedMagazineId(
        consumerId: String,
        magazineId: String
    ): UpdateResponse {
        val script = "ctx._source.scrapped_magazine_ids.add(params.new_scrapped_magazine_id)"
        val param = mapOf("new_scrapped_magazine_id" to magazineId)

        return updateWithScript(consumerId, script, param)
    }

    fun updateDate(
        consumerId: String,
        dt: String
    ): UpdateResponse {
        val script = "ctx._source.up_dt = params.new_dt"
        val param = mapOf("new_dt" to dt)

        return updateWithScript(consumerId, script, param)
    }

    fun updateProfile(
        consumerId: String,
        nickName: String,
        content: String,
        profileUrl: String,
        dt: String
    ): UpdateResponse {
        val script = "ctx._source.consumer_nickname = params.consumer_nickname;" +
                "ctx._source.profile_content = params.profile_content;" +
                "ctx._source.profile_url = params.profile_url;" +
                "ctx._source.up_dt = params.new_dt"
        val params = mapOf(
            "consumer_nickname" to nickName,
            "profile_content" to content,
            "profile_url" to profileUrl,
            "new_dt" to dt
        )

        return updateWithScript(consumerId, script, params)
    }
}