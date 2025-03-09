package com.example.demo.cqrs

import community.flock.wirespec.integration.spring.kotlin.configuration.EnableWirespecController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableWirespecController
@EntityScan(basePackages = ["org.axonframework.eventhandling.tokenstore.jpa", "com.example.demo.cqrs.entity"])
class CqrsDemoOrderQueryModelApplication

fun main(args: Array<String>) {
    runApplication<CqrsDemoOrderQueryModelApplication>(*args)
}
