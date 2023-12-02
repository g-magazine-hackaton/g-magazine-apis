package com.rocketsorry.gmagazine.service.response

data class StoreResponse(
    val success: Boolean,
    val message: String,
    val result: String?,
) {
    companion object {
        fun of(resSuccess: Boolean, resMessage: String, resId: String): StoreResponse {
            return StoreResponse(
                resSuccess, resMessage, resId
            )
        }
    }
}
