package com.example.demo.cqrs.entity

import com.example.demo.cqrs.events.OrderCreated
import community.flock.wirespec.generated.kotlin.OrderDTO
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity
data class OrderEntity(
    @Id
    val orderId: UUID,
    val totalAmount: BigDecimal,
    val amountPaid: BigDecimal,
) {
    constructor() : this(UUID.randomUUID(), BigDecimal.ZERO, BigDecimal.ZERO)

    fun toOrderDTO() = OrderDTO(orderId.toString(), totalAmount.toDouble(), amountPaid.toDouble())

    companion object {
        fun fromOrderCreatedEvent(event: OrderCreated) = OrderEntity(event.orderId, event.totalAmount, event.amountPaid)
    }
}
