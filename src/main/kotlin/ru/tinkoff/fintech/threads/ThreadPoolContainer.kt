package ru.tinkoff.fintech.threads

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThreadPoolContainer {

    private var value1: Int = 0
    private var value2: Int = 0
    private var value3: Int = 0

    private val timeResult = mutableListOf<Pair<String, Long>>()

    fun printResult() {
        for (i in timeResult.sortedBy { it.second }) {
            println(i.first + ": " + i.second + " ns")
        }
    }

    fun startTest() {
        val poolSizes = listOf(10, 20, 30)
        val executors = mutableListOf<ExecutorService>()

        for (size in poolSizes) {
            val executor: ExecutorService = Executors.newFixedThreadPool(size)
            executors += executor
        }

        executors[0].execute {
            val start = System.nanoTime()
            while (value1 < 1_000_000) {
                ++value1
            }
            val end = System.nanoTime()
            saveTimeResult("Executor with size = 10", end - start)
        }

        executors[1].execute {
            val start = System.nanoTime()
            while (value2 < 1_000_000) {
                ++value2
            }
            val end = System.nanoTime()
            saveTimeResult("Executor with size = 20", end - start)
        }

        executors[2].execute {
            val start = System.nanoTime()
            while (value3 < 1_000_000) {
                ++value3
            }
            val end = System.nanoTime()
            saveTimeResult("Executor with size = 30", end - start)
        }

        shutdown(executors)
    }


    @Synchronized
    private fun saveTimeResult(executorName: String, time: Long) {
        timeResult.add(Pair(executorName, time))
    }

    private fun shutdown(executors: List<ExecutorService>) {
        // Wait for time saving (just in case).
        Thread.sleep(100)

        for (executor in executors) {
            executor.shutdown()
        }
    }
}