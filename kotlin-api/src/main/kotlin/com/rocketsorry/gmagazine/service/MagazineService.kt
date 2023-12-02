package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.persistence.doc.MagazineDoc
import com.rocketsorry.gmagazine.persistence.enum.IdField
import com.rocketsorry.gmagazine.persistence.repository.impl.ConsumerRepository
import com.rocketsorry.gmagazine.persistence.repository.impl.FolderRepository
import com.rocketsorry.gmagazine.persistence.repository.impl.GoodsRepository
import com.rocketsorry.gmagazine.persistence.repository.impl.MagazineRepository
import com.rocketsorry.gmagazine.service.request.LikeRequest
import com.rocketsorry.gmagazine.service.request.MagazineRequest
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.StoreResponse
import com.rocketsorry.gmagazine.service.response.UpdateResponse
import com.rocketsorry.gmagazine.service.response.enums.Message
import org.springframework.stereotype.Service
import java.util.*

@Service
class MagazineService(
    private val magazineRepository: MagazineRepository,
    private val goodsRepository: GoodsRepository,
    private val folderRepository: FolderRepository,
    private val consumerRepository: ConsumerRepository,
) {
    fun getMagazineDetail(
        magazineId: String,
        consumerId: String,
    ): FetchResponse {
        val consumerLikes = consumerRepository.findById(consumerId).searchHits[0].content.likedMagazineIds
        val magazineInfo = magazineRepository.findById(magazineId).searchHits[0].content
        val goodsInfo =
            magazineInfo.goodsIds?.let { goodsRepository.findByIds(it).searchHits.map { it.content }.toList() }
        val isLike = consumerLikes?.contains(magazineId)

        val responseData = mapOf(
            "magazine" to magazineInfo,
            "goods" to goodsInfo,
            "isLike" to isLike
        )

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
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

    fun saveMagazine(
        req: MagazineRequest
    ): StoreResponse {
        val newMagazineId = generateRandomMagazineId()
        val magazineDoc = MagazineDoc(
            docId = newMagazineId,
            magazineId = newMagazineId,
            consumerId = req.consumerId,
            magazineContent = req.magazineContent,
            likedCnt = 0,
            folderId = req.folderId,
            goodsIds = req.goodsIds,
            photoUrls = req.photoUrls,
            upDt = req.upDt
        )

        val response = magazineRepository.save(magazineDoc)

        return StoreResponse(true, Message.SUCCESS.content, response.magazineId)
    }

    fun likeMagazine(
        req: LikeRequest
    ): UpdateResponse {
        val likeUpdate = magazineRepository.updateLikeCount(req.magazineId)
        val likeListUpdate = consumerRepository.updateLikeList(req.consumerId, req.magazineId)

        val storeResult = mapOf(
            "likeCountUpdate" to likeUpdate.result.toString(),
            "likeListUpdate" to likeListUpdate.result.toString()
        )

        return UpdateResponse(true, Message.SUCCESS.content, storeResult)
    }


    private fun generateRandomMagazineId(): String {
        // Generate a random string for magazine_id
        val leftLimit = 97 // letter 'a'
        val rightLimit = 122 // letter 'z'
        val targetStringLength = 10
        val random = Random()
        val generatedString: String = random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength.toLong())
            .collect({ StringBuilder() }, java.lang.StringBuilder::appendCodePoint, java.lang.StringBuilder::append)
            .toString()
        return "magazine_$generatedString"
    }

}