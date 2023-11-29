package com.rocketsorry.gmagazine.controller

import com.rocketsorry.gmagazine.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    /**
     * return type들은 테스트용으로 아무렇게나 넣은거니 무시해주세요~
     */

    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<Any> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok().body(users)
    }

    @GetMapping("/save-test")
    fun saveUser(): ResponseEntity<Any> {
        val res = userService.saveUser()
        return ResponseEntity.ok().body(res)
    }
}
