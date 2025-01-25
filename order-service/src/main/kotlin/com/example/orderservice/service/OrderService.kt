package com.example.orderservice.service

import com.example.orderservice.dto.OrderRequestDto
import com.example.orderservice.model.Order

interface OrderService {
    fun getAll() : List<Order>
    fun getById(id: Long) : Order
    fun add(order : Order) : Order
    fun delete(id : Long)
    fun edit(id : Long, order : Order) : Order
}