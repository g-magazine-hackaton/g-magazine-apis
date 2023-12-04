package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.ConsumerService
import com.rocketsorry.gmagazine.service.request.FollowRequest
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.UpdateResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/follow")
    fun addFollow(
        @RequestBody followRequest: FollowRequest
    ): ResponseEntity<UpdateResponse> {
        val response = consumerService.addFollow(followRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/rank")
    fun getConsumerRank(): ResponseEntity<FetchResponse> {
        val response = consumerService.getRank()
        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/{consumerId}/scraps")
    fun getConsumerRank(@PathVariable consumerId: String): ResponseEntity<FetchResponse> {
        val response = consumerService.getScrappedMagazines(consumerId)
        return ResponseEntity.ok()
            .body(response)
    }

    @PostMapping("/{consumerId}/scraps/{magazineId}")
    fun scrapMagazine(
        @PathVariable consumerId: String,
        @PathVariable magazineId: String
    ): ResponseEntity<UpdateResponse> {
        val response = consumerService.scrapMagazine(consumerId, magazineId)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}