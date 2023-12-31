package com.rocketsorry.gmagazine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GMagazineApplication

fun main(args: Array<String>) {
    runApplication<GMagazineApplication>(*args)
}
