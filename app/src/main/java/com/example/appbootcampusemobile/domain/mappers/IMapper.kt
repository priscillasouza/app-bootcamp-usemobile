package com.example.appbootcampusemobile.domain.mappers

interface IMapper<E, T> {
    fun transform(entity: E): T
    fun transform(list: List<E>): List<T> = list.map { transform(it) }
}