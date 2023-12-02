package com.rocketsorry.gmagazine.service.response

data class UpdateResponse(
    val success: Boolean,
    val message: String,
    val result: Map<String, String>?,
) {
    companion object {
        fun of(resSuccess: Boolean, resMessage: String, result: Map<String, String>): UpdateResponse {
            return UpdateResponse(
                resSuccess, resMessage, result
            )
        }
    }
}
