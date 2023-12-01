package com.rocketsorry.gmagazine.service.response

data class FetchResponse(
    val success: Boolean,
    val message: String,
    val data: Map<String, Any?>
) {
    companion object {
        fun of(resSuccess: Boolean, resMessage: String, resData: Map<String, Any?>): FetchResponse {
            return FetchResponse(
                resSuccess, resMessage, resData
            )
        }
    }
}