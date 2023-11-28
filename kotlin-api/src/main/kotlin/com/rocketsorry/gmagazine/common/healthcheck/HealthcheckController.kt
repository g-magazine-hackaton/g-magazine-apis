package com.rocketsorry.gmagazine.common.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthcheckController(
    private val elasticsearchService: ElasticsearchService
) {
    @GetMapping("/healthcheck")
    fun healthcheck(): String = "ok"

    @GetMapping("/healthcheck/es")
    fun healthcheckElasticsearch(): String {
        return elasticsearchService.healthcheck();
    }
}