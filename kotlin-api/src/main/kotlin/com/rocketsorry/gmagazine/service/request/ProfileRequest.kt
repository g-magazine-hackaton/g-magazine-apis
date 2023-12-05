package com.rocketsorry.gmagazine.service.request

import java.time.LocalDate

data class ProfileRequest(
    val consumerId: String,
    val nickName: String,
    val content: String,
    val photoUrl: String,
    val upDt: LocalDate
)
