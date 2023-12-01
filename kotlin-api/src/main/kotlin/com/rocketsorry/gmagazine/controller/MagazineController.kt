package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.MagazineService
import com.rocketsorry.gmagazine.service.response.FetchResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/magazine")
class MagazineController(
    private val magazineService: MagazineService
) {

    @GetMapping("/detail")
    fun getMagazine(
        magazineId: String
    ): ResponseEntity<FetchResponse> {
        val response = magazineService.getMagazineDetail(magazineId)
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


}
