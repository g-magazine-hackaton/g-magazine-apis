package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.persistence.repository.impl.GoodsRepository
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.enums.Message
import org.springframework.stereotype.Service

@Service
class GoodsService(
    private val goodsRepository: GoodsRepository
) {
    fun getGoods(): FetchResponse {
        val goods = goodsRepository.findAll().searchHits
            .map { it.content }.toList()

        val responseData = mapOf("goods" to goods)

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

}