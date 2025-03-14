package com.example.demo.cqrs

import com.example.demo.cqrs.entity.OrderEntity
import com.example.demo.cqrs.order.query.api.FindAllOrdersQuery
import com.example.demo.cqrs.order.query.api.FindOrderByIdQuery
import community.flock.wirespec.generated.kotlin.OrderDTOEndpoint
import community.flock.wirespec.generated.kotlin.OrdersDTOEndpoint
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class OrderEndpoint(
    val queryGateway: QueryGateway,
) : OrderDTOEndpoint.Handler,
    OrdersDTOEndpoint.Handler {
    override suspend fun orderDTO(request: OrderDTOEndpoint.Request): OrderDTOEndpoint.Response<*> =
        OrderDTOEndpoint.Response200(
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

    override suspend fun ordersDTO(request: OrdersDTOEndpoint.Request): OrdersDTOEndpoint.Response<*> =
        OrdersDTOEndpoint.Response200(
            queryGateway
                .query(
                    FindAllOrdersQuery(),
                    ResponseTypes.multipleInstancesOf(OrderEntity::class.java),
                ).join()
                .map(OrderEntity::toOrderDTO),
        )
}
