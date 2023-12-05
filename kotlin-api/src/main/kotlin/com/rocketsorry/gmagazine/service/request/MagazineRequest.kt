package com.rocketsorry.gmagazine.service.request

data class MagazineRequest(
    val consumerId: String,
    val magazineContent: String,
    val likedCnt: Int?,
    val folderId: String?,
    val goodsIds: List<String>?,
    val photoUrls: List<String>,
    val upDt: String
)
