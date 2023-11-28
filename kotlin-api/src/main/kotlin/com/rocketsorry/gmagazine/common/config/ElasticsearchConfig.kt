package com.rocketsorry.gmagazine.common.config

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

/**
 * restClient를 아래 설정대로 빈으로 등록한다.
 */
@Configuration
class ElasticsearchConfig(private val environment: Environment) {

    @Bean
    fun restClient(): RestClient {
        val host: String? = environment.getProperty("elasticsearch.host")
        val port: Int = environment.getProperty("elasticsearch.port")!!.toInt()

        return RestClient.builder(HttpHost(host, port, "http")).build()
    }
}