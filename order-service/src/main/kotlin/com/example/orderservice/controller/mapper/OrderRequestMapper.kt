package com.example.orderservice.controller.mapper

import com.example.orderservice.dto.OrderRequestDto
import com.example.orderservice.model.Order
import org.springframework.stereotype.Component

@Component
class OrderRequestMapper : MapperDto<OrderRequestDto, Order> {

    override fun mapToModel(source: OrderRequestDto): Order {
        return Order(
            id = null,
            name = source.name ?: "Unknown",
            email = source.email ?: "unknown@example.com"
        )
    }

    override fun mapToDto(source: Order): OrderRequestDto {
        return OrderRequestDto(
            name = source.name,
            email = source.email
        )
    }
}