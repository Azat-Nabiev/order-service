package com.example.orderservice.controller.mapper

interface MapperDto<S, T> {
    fun mapToModel(source: S): T
    fun mapToDto(source: T): S
}