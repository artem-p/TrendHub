package ru.artempugachev.mobileui.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}