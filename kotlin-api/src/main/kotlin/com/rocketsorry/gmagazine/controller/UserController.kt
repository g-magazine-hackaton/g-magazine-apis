package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<Any> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok().body(users)
    }
}
