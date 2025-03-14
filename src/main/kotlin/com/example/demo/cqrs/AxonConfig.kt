package com.example.demo.cqrs

import org.axonframework.messaging.interceptors.LoggingInterceptor
import org.axonframework.queryhandling.QueryBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {
    @Autowired
    fun configureQueryBus(queryBus: QueryBus): QueryBus {
        queryBus.registerHandlerInterceptor(LoggingInterceptor())
        return queryBus
    }
}
