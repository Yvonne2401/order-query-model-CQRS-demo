package com.example.demo.cqrs.events

import java.math.BigDecimal
import java.util.*

data class OrderCreated(
    val cartId: UUID,
    val orderId: UUID,
    val orderLines: List<OrderLine>,
    val totalAmount: BigDecimal,
    val amountPaid: BigDecimal,
)

data class OrderLine(
    val productId: UUID,
    val quantity: Int,
    val price: BigDecimal,
)
