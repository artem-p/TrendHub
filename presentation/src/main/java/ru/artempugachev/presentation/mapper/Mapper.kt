package ru.artempugachev.presentation.mapper

interface Mapper<out V, in D> {

    fun mapToView(domain: D): V

}