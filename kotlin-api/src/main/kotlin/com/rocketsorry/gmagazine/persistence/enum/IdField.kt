package com.rocketsorry.gmagazine.persistence.enum

enum class IdField(
    val fieldName: String
) {
    CONSUMER("consumer_id"),
    MAGAZINE("magazine_id"),
    FOLDER("folder_id"),
    GOODS("goods_id"),
    CONSUMER_RANK("consumer_score"),
    CONSUMER_UPDT("up_dt")
}