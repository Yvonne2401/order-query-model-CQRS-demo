package com.example.demo.cqrs

import com.example.demo.cqrs.entity.OrderEntity
import com.example.demo.cqrs.order.query.api.FindAllOrdersQuery
import community.flock.wirespec.generated.kotlin.OrderDTO
import community.flock.wirespec.generated.kotlin.OrderDTOEndpoint
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderEndpoint(
    val queryGateway: QueryGateway,
) : OrderDTOEndpoint.Handler {
    override suspend fun orderDTO(request: OrderDTOEndpoint.Request): OrderDTOEndpoint.Response<*> =
        OrderDTOEndpoint.Response200(
            queryGateway
                .query(
                    FindAllOrdersQuery(),
                    ResponseTypes.multipleInstancesOf(
                        OrderEntity::class.java,
                    ),
                ).join()
                .map {
                    OrderDTO(it.orderId.toDouble(), it.amountPaid.toDouble(), it.totalAmount.toDouble())
                },
        )
}
