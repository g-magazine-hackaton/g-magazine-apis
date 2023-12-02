package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.GoodsService
import com.rocketsorry.gmagazine.service.response.FetchResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/good")
class GoodsController(
    private val goodsService: GoodsService
) {

    @GetMapping("/goods")
    fun getGoods(): ResponseEntity<FetchResponse> {
        val response = goodsService.getGoods()

        return ResponseEntity.ok().body(response)
    }
}