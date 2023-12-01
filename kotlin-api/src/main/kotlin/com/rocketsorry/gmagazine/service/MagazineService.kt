package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.persistence.enum.IdField
import com.rocketsorry.gmagazine.persistence.repository.impl.FolderRepository
import com.rocketsorry.gmagazine.persistence.repository.impl.GoodsRepository
import com.rocketsorry.gmagazine.persistence.repository.impl.MagazineRepository
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.enums.Message
import org.springframework.stereotype.Service

@Service
class MagazineService(
    private val magazineRepository: MagazineRepository,
    private val goodsRepository: GoodsRepository,
    private val folderRepository: FolderRepository,
) {
    fun getMagazineDetail(
        magazineId: String
    ): FetchResponse {
        val magz = magazineRepository.findById(magazineId).searchHits[0].content
        val goodsInfo = magz.goodsIds?.let { goodsRepository.findByIds(it).searchHits.map { it.content }.toList() }

        val responseData = mapOf(
            "magazine" to magz,
            "goods" to goodsInfo
        )

        return FetchResponse.of(true, "성공했습니다", responseData)
    }

    fun getMagazineAll(
        consumerId: String
    ): FetchResponse {
        val magazines =
            magazineRepository.findAllById(consumerId, IdField.CONSUMER).searchHits.map { it.content }.toList()
        val responseData = mapOf(
            "magazines" to magazines
        )

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun getFolders(
        consumerId: String
    ): FetchResponse {
        val folders = folderRepository.findAllById(consumerId, IdField.CONSUMER).searchHits.map { it.content }.toList()
        val responseData = mapOf(
            "folders" to folders
        )

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

}