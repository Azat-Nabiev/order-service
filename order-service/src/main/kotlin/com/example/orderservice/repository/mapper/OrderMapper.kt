package com.example.orderservice.repository.mapper

import com.example.orderservice.model.Order
import com.example.orderservice.repository.OrderPm

class OrderMapper : Mapper<Order, OrderPm> {
    override fun mapToPm(source: Order): OrderPm {
        return OrderPm(
            name = source.name ?: "no name",
            email = source.email ?: "no email"
        )
    }

    override fun mapToModel(source: OrderPm): Order {
        return Order(
            id = source.id,
            name = source.name,
            email = source.email
        )
    }
}