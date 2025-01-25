package com.example.orderservice.controller.mapper

import com.example.orderservice.dto.OrderResponseDto
import com.example.orderservice.model.Order
import org.springframework.stereotype.Component

@Component
class OrderResponseMapper : MapperDto<OrderResponseDto, Order> {

    override fun mapToModel(source: OrderResponseDto): Order {
        return Order(
            id = source.id,
            name = source.name,
            email = source.email
        )
    }

    override fun mapToDto(source: Order): OrderResponseDto {
        return OrderResponseDto(
            id = source.id,
            name = source.name,
            email = source.email
        )
    }
}