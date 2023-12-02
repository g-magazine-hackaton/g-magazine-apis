package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.MagazineService
import com.rocketsorry.gmagazine.service.request.LikeRequest
import com.rocketsorry.gmagazine.service.request.MagazineRequest
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.StoreResponse
import com.rocketsorry.gmagazine.service.response.UpdateResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/magazine")
class MagazineController(
    private val magazineService: MagazineService
) {

    @GetMapping("/detail")
    fun getMagazine(
        magazineId: String,
        consumerId: String,
    ): ResponseEntity<FetchResponse> {
        val response = magazineService.getMagazineDetail(magazineId, consumerId)
        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/all")
    fun getConsumerAllMagazine(
        consumerId: String
    ): ResponseEntity<FetchResponse> {
        val response = magazineService.getMagazineAll(consumerId)
        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/folders")
    fun getFolders(
        consumerId: String
    ): ResponseEntity<FetchResponse> {
        val response = magazineService.getFolders(consumerId)
        return ResponseEntity.ok()
            .body(response)
    }

    @PostMapping("/save")
    fun saveMagazine(
        @RequestBody magazineRequest: MagazineRequest
    ): ResponseEntity<StoreResponse> {
        val saveMagazine = magazineService.saveMagazine(magazineRequest)

        return ResponseEntity.status(HttpStatus.CREATED).body(saveMagazine)
    }

    @PostMapping("/like")
    fun likeMagazine(
        @RequestBody likeRequest: LikeRequest
    ): ResponseEntity<UpdateResponse> {
        val likeMagazine = magazineService.likeMagazine(likeRequest)

        return ResponseEntity.status(HttpStatus.CREATED).body(likeMagazine)
    }

}
