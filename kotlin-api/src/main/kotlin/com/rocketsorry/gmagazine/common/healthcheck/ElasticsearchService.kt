package com.rocketsorry.gmagazine.common.healthcheck

import com.rocketsorry.gmagazine.common.config.ElasticsearchConfig
import org.elasticsearch.client.RestClient
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val esClientRegister: ElasticsearchConfig, private val esClient: RestClient) {

    fun healthcheck(): String {
        val isRunning = esClient.isRunning
        return if (isRunning) esClient.nodes[0].toString() + " Host에 연결되었습니다." else "실패"
    }
}