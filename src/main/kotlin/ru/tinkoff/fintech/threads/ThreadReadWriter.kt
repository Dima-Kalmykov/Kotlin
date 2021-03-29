package ru.tinkoff.fintech.threads

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ThreadReadWriter {

    private var value = 0
    private val lock = ReentrantLock()

    fun startTest() {
        val mainThread = Thread {
            increment()
        }

        for (i in 1..3) {
            val reader = Thread {
                while (getValue() < 5) {
                    println("Reader $i read value ${getValue()}")
                    Thread.sleep(999)
                }
            }.apply { name = "Reader $i" }

            reader.start()
        }

        mainThread.start()
    }

    private fun getValue(): Int {
        lock.withLock {
            return value
        }
    }

    private fun increment() {
        while (value < 5) {
            ++value
            println("\nI has been increased!\n")
            Thread.sleep(1000)
        }
    }
}