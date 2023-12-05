package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

data class GoodsDoc(
    @Id
    var id: String? = null,

    @Field(name = "goods_id", type = FieldType.Keyword)
    var goodsId: String?,

    @Field(name = "goods_name", type = FieldType.Keyword)
    var goodsName: String?,

    @Field(name = "goods_price", type = FieldType.Integer)
    var goodsPrice: Int?,

    @Field(name = "goods_page_url", type = FieldType.Keyword)
    var goodsPageUrl: String?,

    @Field(name = "goods_photo_url", type = FieldType.Keyword)
    var goodsPhotoUrl: String?,

    @Field(name = "goods_selected_option", type = FieldType.Keyword)
    var goodsOption: String?,

    @Field(name = "up_dt", type = FieldType.Date, format = [DateFormat.date_hour_minute_second])
    var upDt: String
)
