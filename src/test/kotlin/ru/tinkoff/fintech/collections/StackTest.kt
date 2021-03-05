package ru.tinkoff.fintech.collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

internal class StackTest {

    @Test
    @DisplayName("stackOf() test")
    fun stackOfTest() {
        val intStack = stackOf(1, 2, 3, 4)
        assertEquals("top -> 4 -> 3 -> 2 -> 1", intStack.toString())
        val stringStack = stackOf(listOf("!", "world", ",", "Hello"))
        assertEquals("top -> Hello -> , -> world -> !", stringStack.toString())
    }

    @Test
    @DisplayName("Map test")
    fun mapTest() {
        val stack = stackOf(1, 2, 3)
        val newStack1 = stack.map { it * 2 }
        assertEquals("top -> 6 -> 4 -> 2", newStack1.toString())
        val newStack2 = newStack1.map { it + 1 }
        assertEquals("top -> 7 -> 5 -> 3", newStack2.toString())
    }

    @Test
    @DisplayName("Size test")
    fun sizeTest() {
        val stack = stackOf("1", "2", "3")
        assertEquals(3, stack.size)
        assertFalse(stack.isEmpty())
        for (i in 1..3) {
            assertEquals(4 - i, stack.size)
            assertFalse(stack.isEmpty())
            stack.pop()
        }

        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
    }

    @Test
    @DisplayName("Peek test")
    fun peekTest() {
        val stack = stackOf(3, 2)
        assertEquals(2, stack.peek())
        stack.pop()
        stack.pop()
        assertThrows(EmptyStackException::class.java) { stack.peek() }
        stack.push(2)
        assertEquals(2, stack.peek())
    }

    @Test
    @DisplayName("Push test")
    fun pushTest() {
        val stack = stackOf(1, 2, 3)
        stack.push(2)
        assertEquals("top -> 2 -> 3 -> 2 -> 1", stack.toString())
        assertEquals(2, stack.pop())
        stack.push(-3)
        stack += 2
        assertEquals("top -> 2 -> -3 -> 3 -> 2 -> 1", stack.toString())
        val emptyStack: Stack<String> = stackOf()
        emptyStack.push("Hello")
        emptyStack.push("world")
        assertEquals("top -> world -> Hello", emptyStack.toString())

    }

    @Test
    @DisplayName("Pop test")
    fun popTest() {
        val stack = stackOf(4, 2, 1)
        assertEquals(1, stack.pop())
        assertEquals("top -> 2 -> 4", stack.toString())
        assertEquals(2, stack.pop())
        assertEquals("top -> 4", stack.toString())
        assertEquals(4, stack.pop())
        assertEquals("", stack.toString())
        assertThrows(EmptyStackException::class.java) { stack.pop() }
        stack.push(10)
        assertEquals(10, stack.pop())
    }
}