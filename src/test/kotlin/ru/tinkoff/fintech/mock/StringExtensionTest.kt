package ru.tinkoff.fintech.mock

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.security.InvalidParameterException
import kotlin.test.assertFailsWith

internal class StringExtensionTest {

    @Test
    fun `correct string`() {
        mockkStatic("ru.tinkoff.fintech.mock.StringExtensionKt")
        val correctStr = "Hello world"
        val expected = "HELLO WORLD"
        every { correctStr.toUpperAll() } returns expected
        assertEquals(expected, correctStr.toUpperAll())
        verify { correctStr.toUpperAll() }
    }

    @Test
    fun `incorrect string`() {
        val incorrectStr = "Hello, world"
        val exception = assertFailsWith<InvalidParameterException>(
            block = {
                incorrectStr.toUpperAll()
            }
        )
        assertEquals("String \"$incorrectStr\" must contain only letters or spaces",
            exception.message)
    }
}