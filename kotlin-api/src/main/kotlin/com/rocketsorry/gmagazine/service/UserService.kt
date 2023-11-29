package com.rocketsorry.gmagazine.service

import com.rocketsorry.gmagazine.entity.UserEntity
import com.rocketsorry.gmagazine.reposigory.ElasticsearchRepositoryImpl
import org.apache.catalina.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val elasticsearchRepositoryImpl: ElasticsearchRepositoryImpl) {

    /**
     * es 응답은 JsonObject 이다.
     * 중간 매퍼를 통해 비즈니스 도메인 모델로 컨벌팅할 필요가 있다.
     * 다만, 서비스 규모상 컨벌팅 없이 데이터만 빠르게 서빙하는 것을 목표로 하는 것이 좋겠다.
     */
    fun getAllUsers(): List<UserEntity> {
        val users = elasticsearchRepositoryImpl.getDocumentsByIndex<UserEntity>("user")
        return users.map { user -> UserEntity(user.get("username").toString(), user.get("age").toString().toDouble().toInt()) }
    }

    fun saveUser(): String {
        val userJson = """
            {
                "username": "John Doe",
                "age": 30
            }
        """

        return elasticsearchRepositoryImpl.saveDocument<User>("user", null, userJson).toString()
    }
}