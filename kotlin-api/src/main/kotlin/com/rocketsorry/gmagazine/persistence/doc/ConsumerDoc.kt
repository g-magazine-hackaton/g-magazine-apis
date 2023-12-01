package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

data class ConsumerDoc(
    @Id
    var id: String? = null,

    @Field(name = "consumer_id", type = FieldType.Keyword)
    var consumerId: String,

    @Field(name = "consumer_nickname", type = FieldType.Keyword)
    var consumerNickname: String,

    @Field(name = "consumer_rank", type = FieldType.Integer)
    var consumerRank: Int,

    @Field(name = "consumer_score", type = FieldType.Integer)
    var consumerScore: Int,

    @Field(name = "profile_content", type = FieldType.Keyword)
    var profileContent: String,

    @Field(name = "profile_url", type = FieldType.Keyword)
    var profileUrl: String,

    @Field(name = "scrapped_magazine_ids", type = FieldType.Keyword)
    var scrappedMagazineIds: List<String>?,

    @Field(name = "liked_magazine_ids", type = FieldType.Keyword)
    var likedMagazineIds: List<String>?,

    @Field(name = "follower_consumer_ids", type = FieldType.Keyword)
    var followerConsumerIds: List<String>?,

    @Field(name = "following_consumer_ids", type = FieldType.Keyword)
    var followingConsumerIds: List<String>?,

    @Field(name = "up_dt", type = FieldType.Date, format = [DateFormat.date])
    var upDt: LocalDate
)
