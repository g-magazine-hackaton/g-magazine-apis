package com.rocketsorry.gmagazine.common.healthcheck

import org.elasticsearch.client.RestClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthcheckController(
    private val esClient: RestClient
) {
    @GetMapping("/healthcheck")
    fun healthcheck(): String = "ok"

    @GetMapping("/healthcheck/es")
    fun healthcheckElasticsearch(): String {
        val isRunning = esClient.isRunning
        return if (isRunning) esClient.nodes[0].toString() + " Host에 연결되었습니다." else "실패"
    }
}