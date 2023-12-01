package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.ConsumerService
import com.rocketsorry.gmagazine.service.response.FetchResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/consumer")
class ConsumerController(
    private val consumerService: ConsumerService
) {

    @GetMapping("/me")
    fun getMyInfo(
        consumerId: String
    ): ResponseEntity<FetchResponse> {
        val response = consumerService.getMyInfo(consumerId)

        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/detail")
    fun getConsumer(
        consumerId: String,
        myId: String,
    ): ResponseEntity<FetchResponse> {
        val response = consumerService.getConsumer(consumerId, myId)

        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/followers")
    fun getFollowers(
        consumerId: String
    ): ResponseEntity<FetchResponse> {
        val response = consumerService.getFollowers(consumerId)

        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/followings")
    fun getFollowings(
        consumerId: String
    ): ResponseEntity<FetchResponse> {
        val response = consumerService.getFollowings(consumerId)

        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/rank")
    fun getConsumerRank(): ResponseEntity<FetchResponse> {
        val response = consumerService.getRank()
        return ResponseEntity.ok()
            .body(response)
    }
}