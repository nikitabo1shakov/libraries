package com.nikitabolshakov.libraries.data.model

class Counters {

    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}