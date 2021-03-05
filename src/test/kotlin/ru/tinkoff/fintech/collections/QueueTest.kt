package ru.tinkoff.fintech.collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

internal class QueueTest {

    @Test
    @DisplayName("queueOf() test")
    fun queueOfTest() {
        val intQueue = queueOf(1, 2, 3, 4)
        assertEquals("front -> 1 -> 2 -> 3 -> 4 <- back", intQueue.toString())
        val stringQueue = queueOf(listOf("Hello", "world"))
        assertEquals("front -> Hello -> world <- back", stringQueue.toString())
    }

    @Test
    @DisplayName("Map test")
    fun mapTest() {
        val queue = queueOf(1, 2, 3)
        val newQueue1 = queue.map { it * 2 }
        assertEquals("front -> 2 -> 4 -> 6 <- back", newQueue1.toString())
        val newQueue2 = newQueue1.map { it + 1 }
        assertEquals("front -> 3 -> 5 -> 7 <- back", newQueue2.toString())
    }

    @Test
    @DisplayName("Size test")
    fun sizeTest() {
        val queue = queueOf("1", "2", "3")
        assertEquals(3, queue.size)
        assertFalse(queue.isEmpty())
        for (i in 1..3) {
            assertEquals(4 - i, queue.size)
            assertFalse(queue.isEmpty())
            queue.dequeue()
        }

        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
    }

    @Test
    @DisplayName("Peek test")
    fun peekTest() {
        val queue = queueOf(3, 2)
        assertEquals(3, queue.peek())
        queue.dequeue()
        queue.dequeue()
        assertThrows(EmptyStackException::class.java) { queue.peek() }
        queue.enqueue(2)
        assertEquals(2, queue.peek())
    }

    @Test
    @DisplayName("Enqueue test")
    fun enqueueTest() {
        val queue = queueOf(1, 2, 3)
        queue.enqueue(2)
        assertEquals("front -> 1 -> 2 -> 3 -> 2 <- back", queue.toString())
        assertEquals(1, queue.dequeue())
        queue.enqueue(-3)
        queue += 2
        assertEquals("front -> 2 -> 3 -> 2 -> -3 -> 2 <- back", queue.toString())
        val emptyQueue: Queue<String> = queueOf()
        emptyQueue.enqueue("Hello")
        emptyQueue.enqueue("world")
        assertEquals("front -> Hello -> world <- back", emptyQueue.toString())

    }

    @Test
    @DisplayName("Dequeue test")
    fun dequeueTest() {
        val queue = queueOf(4, 2, 1)
        assertEquals(4, queue.dequeue())
        assertEquals("front -> 2 -> 1 <- back", queue.toString())
        assertEquals(2, queue.dequeue())
        assertEquals("front -> 1 <- back", queue.toString())
        assertEquals(1, queue.dequeue())
        assertEquals("", queue.toString())
        assertThrows(EmptyStackException::class.java) { queue.dequeue() }
        queue.enqueue(10)
        assertEquals(10, queue.dequeue())
    }
}