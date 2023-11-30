package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

data class UserDoc(
    @Id
    @Field(type = FieldType.Keyword)
    val id: String,
    @Field(name = "user_id", type = FieldType.Keyword)
    val userId: String,
    @Field(name = "nick_name", type = FieldType.Keyword)
    val nickName: String,

    @Field(name = "inst_dt", type = FieldType.Date, format = [DateFormat.basic_date])
    val instDate: LocalDate,
    @Field(name = "upt_dt", type = FieldType.Date, format = [DateFormat.basic_date])
    val upDate: LocalDate,

    @Field(name = "score", type = FieldType.Integer)
    val score: Int,
    @Field(name = "follow_cnt", type = FieldType.Integer)
    val followCnt: Int,

    @Field(name = "profile", type = FieldType.Keyword)
    val profile: String,
    @Field(name = "content", type = FieldType.Keyword)
    val content: String,


    )
