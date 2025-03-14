package com.example.demo.cqrs

import com.example.demo.cqrs.entity.OrderEntity
import com.example.demo.cqrs.order.query.api.FindAllOrdersQuery
import com.example.demo.cqrs.order.query.api.FindOrderByIdQuery
import com.example.demo.cqrs.repository.OrderRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class OrderQueryHandler(
    val orderRepository: OrderRepository,
) {
    @QueryHandler
    fun on(query: FindAllOrdersQuery): List<OrderEntity> = orderRepository.findAll()

    @QueryHandler
    fun on(query: FindOrderByIdQuery): OrderEntity? = orderRepository.findById(query.orderId).orElse(null)
}
