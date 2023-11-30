package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.persistence.doc.UserDoc
import com.rocketsorry.gmagazine.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(private val userService: UserService) {

    @GetMapping("/user")
    fun getUser(
        userId: String
    ): UserDoc {
        return userService.getUsers(userId)
    }

}
