package com.example.orderservice.repository

import jakarta.persistence.*


@Entity
@Table(name = "order_table")
data class OrderPm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String,
    var email: String) {
}