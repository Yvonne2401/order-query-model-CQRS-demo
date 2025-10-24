package com.example.demo.cqrs

import com.example.demo.cqrs.entity.OrderEntity
import com.example.demo.cqrs.order.query.api.FindAllOrdersQuery
import com.example.demo.cqrs.order.query.api.FindOrderByIdQuery
import community.flock.wirespec.generated.endpoint.OrderDTO
import community.flock.wirespec.generated.endpoint.OrdersDTO
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class OrderEndpoint(
    val queryGateway: QueryGateway,
) : OrderDTO.Handler,
    OrdersDTO.Handler {
    override suspend fun orderDTO(request: OrderDTO.Request): OrderDTO.Response<*> =
        OrderDTO.Response200(
            queryGateway
                .query(
                    FindOrderByIdQuery(
                        UUID.fromString(
                            request.path.orderId,
                        ),
                    ),
                    ResponseTypes.instanceOf(OrderEntity::class.java),
                ).join()
                .toOrderDTO(),
        )

    override suspend fun ordersDTO(request: OrdersDTO.Request): OrdersDTO.Response<*> =
        OrdersDTO.Response200(
            queryGateway
                .query(
                    FindAllOrdersQuery(),
                    ResponseTypes.multipleInstancesOf(OrderEntity::class.java),
                ).join()
                .map(OrderEntity::toOrderDTO),
        )
}
