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
    ): UpdateResponse {
        val script = "ctx._source.liked_magazine_ids.add(params.new_magazine_id)"
        val param = mapOf("new_magazine_id" to magazinId)

        return updateWithScript(docId, script, param)
    }

    fun updateFollowingList(
        consumerId: String,
        myId: String,
    ): UpdateResponse {
        val script = "ctx._source.following_consumer_ids.add(params.new_consumer_id)"
        val param = mapOf("new_consumer_id" to consumerId)

        return updateWithScript(myId, script, param)
    }

    fun updateFollowerList(
        consumerId: String,
        myId: String
    ): UpdateResponse {
        val script = "ctx._source.follower_consumer_ids.add(params.new_consumer_id)"
        val param = mapOf("new_consumer_id" to myId)

        return updateWithScript(consumerId, script, param)
    }
}