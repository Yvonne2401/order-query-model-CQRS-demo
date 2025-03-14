package com.example.demo.cqrs.order.query.api

import java.util.UUID

class FindAllOrdersQuery

data class FindOrderByIdQuery(
    val orderId: UUID,
)
