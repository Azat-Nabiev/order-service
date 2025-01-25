package com.example.orderservice.repository.mapper

interface Mapper<S, T> {
    fun mapToPm(source: S): T
    fun mapToModel(source: T): S
}

object ObjectMapperUtil {
    fun <S, T> mapToPm(source : S, mapper: Mapper<S, T>) : T {
        return mapper.mapToPm(source)
    }

    fun <T, S> mapToModel(source: T, mapper: Mapper<S, T>) : S {
        return mapper.mapToModel(source)
    }
}