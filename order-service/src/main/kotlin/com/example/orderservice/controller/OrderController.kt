package com.example.orderservice.controller

import com.example.orderservice.controller.mapper.OrderRequestMapper
import com.example.orderservice.controller.mapper.OrderResponseMapper
import com.example.orderservice.dto.OrderRequestDto
import com.example.orderservice.dto.OrderResponseDto
import com.example.orderservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/order")
class OrderController(private val orderService: OrderService,
                      private val orderRequestMapper: OrderRequestMapper,
                      private val orderResponseMapper: OrderResponseMapper
) {


    @GetMapping
    fun getOrders() : ResponseEntity<List<OrderResponseDto>> {
        val orders = orderService.getAll();
        return ResponseEntity.ok(orders.map { order -> orderResponseMapper.mapToDto(order) })
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id : Long) : ResponseEntity<OrderResponseDto> {
        val order = orderService.getById(id)
        return ResponseEntity.ok(orderResponseMapper.mapToDto(order))
    }

    @PostMapping
    fun addOrder(@RequestBody orderRequestDto : OrderRequestDto) : ResponseEntity<OrderResponseDto> {
        val order = orderService.add(orderRequestMapper.mapToModel(orderRequestDto))

        return ResponseEntity.ok(orderResponseMapper.mapToDto(order))
    }

    @PutMapping("/{id}")
    fun editOrder(@PathVariable id : Long, @RequestBody orderRequestDto: OrderRequestDto) : ResponseEntity<OrderResponseDto> {
        val order = orderService.edit(id, orderRequestMapper.mapToModel(orderRequestDto))
        return ResponseEntity.ok(orderResponseMapper.mapToDto(order))
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id : Long) {
        orderService.delete(id)
    }
}