package com.rocketsorry.gmagazine.service.request

data class ProfileRequest(
    val consumerId: String,
    val nickName: String,
    val content: String,
    val photoUrl: String,
    val upDt: String
)
