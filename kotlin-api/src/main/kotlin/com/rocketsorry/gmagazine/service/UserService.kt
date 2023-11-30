package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.persistence.doc.UserDoc
import com.rocketsorry.gmagazine.persistence.repository.impl.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userInfoRepository: UserInfoRepository
) {
    fun getUsers(
        userId: String
    ): UserDoc {

        val response = userInfoRepository.findByUserId(userId).searchHits[0].content

        return response
    }

}