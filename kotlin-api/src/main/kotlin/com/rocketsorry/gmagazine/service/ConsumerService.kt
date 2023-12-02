package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.persistence.enum.IdField
import com.rocketsorry.gmagazine.persistence.repository.impl.ConsumerRepository
import com.rocketsorry.gmagazine.service.request.FollowRequest
import com.rocketsorry.gmagazine.service.response.FetchResponse
import com.rocketsorry.gmagazine.service.response.UpdateResponse
import com.rocketsorry.gmagazine.service.response.enums.Message
import org.springframework.stereotype.Service

@Service
class ConsumerService(
    private val consumerRepository: ConsumerRepository
) {
    fun getMyInfo(
        consumerId: String
    ): FetchResponse {
        val consumer = consumerRepository.findById(consumerId).searchHits[0].content

        val responseData = mapOf("consumer" to consumer)

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun getConsumer(
        consumerId: String,
        myId: String
    ): FetchResponse {
        val consumer = consumerRepository.findById(consumerId).searchHits[0].content
        val isFollow = consumer.followingConsumerIds?.contains(myId)

        val responseData = mapOf(
            "consumer" to consumer,
            "isFollow" to isFollow
        )

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun getFollowers(
        consumerId: String
    ): FetchResponse {
        val followers = consumerRepository.findById(consumerId).searchHits[0].content.followerConsumerIds
        val followersDetail = followers?.let {
            consumerRepository.findByIds(it).searchHits
                .map { it.content }.toList()
        }

        val responseData = mapOf("consumer" to followersDetail)

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun getFollowings(
        consumerId: String
    ): FetchResponse {
        val followings = consumerRepository.findById(consumerId).searchHits[0].content.followingConsumerIds
        val followingsDetail = followings?.let {
            consumerRepository.findByIds(it).searchHits
                .map { it.content }.toList()
        }

        val responseData = mapOf("consumer" to followingsDetail)

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun getRank(): FetchResponse {
        val consumers = consumerRepository.findAllWithSort(IdField.CONSUMER_RANK).searchHits
            .map { it.content }.toList()

        val responseData = mapOf("consumers" to consumers)

        return FetchResponse.of(true, Message.SUCCESS.content, responseData)
    }

    fun addFollow(
        req: FollowRequest
    ): UpdateResponse {
        val addFollower = consumerRepository.updateFollowerList(req.consumerId, req.myId)
        val addFollowing = consumerRepository.updateFollowingList(req.consumerId, req.myId)
        val responseData = mapOf(
            "followerUpdate" to addFollower.result.toString(),
            "followingUpdate" to addFollowing.result.toString()
        )
        return UpdateResponse(true, Message.SUCCESS.content, responseData)
    }

}