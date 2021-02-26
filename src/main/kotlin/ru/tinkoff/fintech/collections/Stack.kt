package ru.tinkoff.fintech.collections

import java.util.*

fun <T> stackOf(data: List<T>): Stack<T> {
    return Stack(data)
}

fun <T> stackOf(vararg data: T): Stack<T> {
    return Stack(data.toList())
}

class Stack<T>(data: List<T> = emptyList()) {

    /**
     * Stack content.
     */
    private var _data: MutableList<T> = data.toMutableList()

    /**
     * Current size of stack.
     */
    val size : Int
        get() = _data.size

    fun isEmpty(): Boolean {
        return size == 0
    }

    /**
     * Add element to stack.
     * @param key item to add
     */
    fun push(key: T) {
        _data.add(key)
    }

    /**
     * Remove top element from stack and return it.
     */
    fun pop(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return _data.removeAt(_data.lastIndex)
    }

    /**
     * Get top element without removing.
     */
    fun peek(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return _data[_data.lastIndex]
    }

    /**
     * Apply transform function to each item in stack.
     * @param transform conversion function
     */
    fun map(transform: (T) -> T) {
        _data = _data.map(transform).toMutableList()
    }

    /**
     * Add element to stack via '+=' operation.
     * @param value item to add
     */
    operator fun plusAssign(value: T) {
        _data.add(value)
    }

    override fun toString(): String {
        if (isEmpty()) {
            return ""
        }

        return "top -> ${_data.reversed().joinToString(" -> ")}"
    }
}