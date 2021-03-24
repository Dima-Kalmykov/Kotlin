package ru.tinkoff.fintech.mock

import org.junit.jupiter.api.Assertions.*

internal class StringHelperTest {

    private val stringHelper = StringHelper()

    @org.junit.jupiter.api.Test
    fun `contains digit, string of digits`() {
        val strWithDigits = "123456789"
        assertTrue(stringHelper.containsOnlyDigits(strWithDigits))
    }

    @org.junit.jupiter.api.Test
    fun `contains digit, string of letters and digits`() {
        val strWithLetters = "1234o56789"
        assertFalse(stringHelper.containsOnlyDigits(strWithLetters))
    }

    @org.junit.jupiter.api.Test
    fun `contains digit, string of letters`() {
        val strWithoutDigits = "Hell to world!"
        assertFalse(stringHelper.containsOnlyDigits(strWithoutDigits))
    }

    @org.junit.jupiter.api.Test
    fun `space amount, string without spaces`() {
        val strWithoutSpaces = "Dima"
        assertEquals(0, stringHelper.getSpaceAmount(strWithoutSpaces))
    }

    @org.junit.jupiter.api.Test
    fun `space amount, string with spaces`() {
        val strWithSpaces = "Hello, world!"
        assertEquals(1, stringHelper.getSpaceAmount(strWithSpaces))
    }
}