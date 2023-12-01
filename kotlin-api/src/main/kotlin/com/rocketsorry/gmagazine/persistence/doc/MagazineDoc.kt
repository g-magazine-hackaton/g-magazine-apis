package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

data class MagazineDoc(
    @Id
    var id: String? = null,

    @Field(name = "magazine_id", type = FieldType.Keyword)
    var magazineId: String,

    @Field(name = "consumer_id", type = FieldType.Keyword)
    var consumerId: String,

    @Field(name = "magazine_content", type = FieldType.Keyword)
    var magazineContent: String,

    @Field(name = "liked_cnt", type = FieldType.Integer)
    var likedCnt: Int?,

    @Field(name = "folder_id", type = FieldType.Keyword)
    var folderId: String?,

    @Field(name = "goods_ids", type = FieldType.Keyword)
    var goodsIds: List<String>?,

    @Field(name = "photo_urls", type = FieldType.Keyword)
    var photoUrls: List<String>,

    @Field(name = "up_dt", type = FieldType.Date, format = [DateFormat.date])
    var upDt: LocalDate
)
