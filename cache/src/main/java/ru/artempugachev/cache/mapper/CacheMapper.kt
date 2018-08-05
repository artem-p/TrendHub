package ru.artempugachev.cache.mapper

interface CacheMapper<C, E> {

    fun mapFromCached(cached: C): E

    fun mapToCached(entity: E): C
}