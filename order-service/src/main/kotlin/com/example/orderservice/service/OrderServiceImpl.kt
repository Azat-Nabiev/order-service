package com.example.orderservice.service

import com.example.orderservice.model.Order
import com.example.orderservice.repository.OrderPm
import com.example.orderservice.repository.OrderRepository
import com.example.orderservice.repository.mapper.ObjectMapperUtil
import com.example.orderservice.repository.mapper.OrderMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {

    @Transactional(readOnly = true)
    override fun getAll(): List<Order> {
        val orders = ArrayList<Order>()

        orderRepository.findAll().forEach{
            orderPm ->
                val order = ObjectMapperUtil.mapToModel(orderPm, OrderMapper())
                orders.add(order)
        }

        return orders
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Order {
        val orderPm = orderRepository.findById(id).orElseThrow{ IllegalStateException("Cant find order by id")}
        return ObjectMapperUtil.mapToModel(orderPm, OrderMapper())
    }

    @Transactional
    override fun add(order: Order): Order {
        val orderPm = ObjectMapperUtil.mapToPm(order, OrderMapper())
        orderRepository.save(orderPm)
        return ObjectMapperUtil.mapToModel(orderPm, OrderMapper())
    }

    @Transactional
    override fun delete(id: Long) {
        orderRepository.deleteById(id)
    }

    @Transactional
    override fun edit(id: Long, order: Order): Order {
        val orderPm = orderRepository.findById(id).orElseThrow{ IllegalStateException("Cannot find order by id")}
        val updatedOrderPm = merge(order, orderPm)
        orderRepository.save(updatedOrderPm)
        return ObjectMapperUtil.mapToModel(updatedOrderPm, OrderMapper())
    }

    private fun merge(order : Order, orderPm : OrderPm) : OrderPm{
        order.email?.let {
            orderPm.email = it
        }

        order.name?.let{
            orderPm.name = it
        }

        return orderPm
    }

}