package com.rocketsorry.gmagazine.common.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


@Configuration
@EnableConfigurationProperties(ElasticsearchProperties::class)
class ElasticsearchConfig(
    private val properties: ElasticsearchProperties
) : ElasticsearchConfiguration() {

    override fun clientConfiguration(): ClientConfiguration {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        return ClientConfiguration.builder()
            .connectedTo(properties.host + ":" + properties.port)
            .withDefaultHeaders(headers)
            .withConnectTimeout(10000)
            .withSocketTimeout(30000)
            .build()
    }
}