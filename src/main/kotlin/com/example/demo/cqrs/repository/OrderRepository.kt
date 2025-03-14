package com.example.demo.cqrs.repository

import com.example.demo.cqrs.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderRepository : JpaRepository<OrderEntity, UUID>
