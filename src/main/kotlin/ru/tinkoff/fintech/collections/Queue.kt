package ru.tinkoff.fintech.collections

import java.util.*

fun <T> queueOf(data: List<T>): Queue<T> {
    return Queue(data)
}

fun <T> queueOf(vararg data: T): Queue<T> {
    return Queue(data.toList())
}


class Queue<T>(data: List<T> = emptyList()) {

    /**
     * Queue content.
     */
    private var _data: MutableList<T> = data.toMutableList()

    /**
     * Current size of queue.
     */
    val size: Int
        get() = _data.size

    fun isEmpty(): Boolean {
        return size == 0
    }

    /**
     * Add element to the back of queue.
     * @param key item to add
     */
    fun enqueue(key: T) {
        _data.add(key)
    }

    /**
     * Remove front element from queue and return it.
     */
    fun dequeue(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return _data.removeAt(0)
    }

    /**
     * Get front element without removing.
     */
    fun peek(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return _data[0]
    }

    /**
     * Apply transform function to each item in queue.
     * @param transform conversion function
     */
    fun map(transform: (T) -> T) {
        _data = _data.map(transform).toMutableList()
    }

    /**
     * Add element to queue via '+=' operation.
     * @param value item to add
     */
    operator fun plusAssign(value: T) {
        _data.add(value)
    }

    override fun toString(): String {
        if (isEmpty()) {
            return ""
        }

        return "front -> ${_data.joinToString(" -> ")} <- back"
    }
}