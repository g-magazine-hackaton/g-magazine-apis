package com.rocketsorry.gmagazine.service.request

data class LikeRequest(
    val magazineId: String,
    val consumerId: String,
    val isPlus: Boolean,
)
