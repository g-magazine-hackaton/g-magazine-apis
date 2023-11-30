package com.rocketsorry.gmagazine.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "elasticsearch")
data class ElasticsearchProperties(
    var host: String = "",
    var port: String = ""
)
