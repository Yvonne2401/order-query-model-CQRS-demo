package com.example.demo.cqrs

import com.example.demo.cqrs.entity.OrderEntity
import com.example.demo.cqrs.events.OrderCreated
import com.example.demo.cqrs.repository.OrderRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@ProcessingGroup("order-projection")
@Service
data class OrderEventHandler(
    val orderRepository: OrderRepository,
) {
    @EventHandler
    fun on(event: OrderCreated) {
        println("Order Created Event: $event")
        orderRepository.save(OrderEntity.fromOrderCreatedEvent(event))
    }
}
