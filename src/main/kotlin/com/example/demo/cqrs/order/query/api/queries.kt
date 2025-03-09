package com.example.demo.cqrs.order.query.api

class FindAllOrdersQuery

data class FindOrderByIdQuery(
    val orderId: String,
)
