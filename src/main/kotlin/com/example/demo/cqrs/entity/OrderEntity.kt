package com.example.demo.cqrs.entity

import com.example.demo.cqrs.events.OrderCreated
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val orderId: Long,
    val totalAmount: BigDecimal,
    val amountPaid: BigDecimal,
) {
    constructor(totalAmount: BigDecimal, amountPaid: BigDecimal) : this(0L, totalAmount, amountPaid)
    constructor() : this(0L, BigDecimal.ZERO, BigDecimal.ZERO)

    companion object {
        fun fromOrderCreatedEvent(event: OrderCreated) = OrderEntity(event.totalAmount, event.amountPaid)
    }
}
