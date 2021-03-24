package ru.tinkoff.fintech.mock

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

internal class BankTest {

    @Test
    fun `test bank properties`() {
        val bank = mockk<Bank> {
            every { name } returns "Super bank"
            every { rating } returns 10
            every { foundationDate } returns LocalDateTime.MIN
            every { employees } returns listOf(
                mockk {
                    every { name } returns "Dima"
                    every { age } returns 19
                    every { id } returns 2
                }
            )
            every { getEmployeeByName("Dima") } returns Employee("Dima", 19, 2)
        }

        assertAll("Bank has only 1 employee",
            { assertEquals("Super bank", bank.name) },
            { assertEquals(10, bank.rating) },
            { assertEquals(LocalDateTime.MIN, bank.foundationDate) },
            { assertEquals(1, bank.employees.size) },
            { assertNotNull(bank.getEmployeeByName("Dima")) },
            { assertEquals("Dima", bank.getEmployeeByName("Dima")?.name) },
            { assertEquals(19, bank.getEmployeeByName("Dima")?.age) },
            { assertEquals(2, bank.getEmployeeByName("Dima")?.id) }
        )
    }

    @Test
    fun `Test 'bank' function`() {
        val bank = bank {
            name = "My bank"
            rating = 5
            foundationDate = LocalDateTime.MAX
            employees = listOf(
                Employee("Dasha", 20),
                Employee("Denis", 29)
            )
        }

        assertEquals("My bank", bank.name)
        assertEquals(5, bank.rating)
        assertNotNull(bank.foundationDate)
        assertEquals(LocalDateTime.MAX, bank.foundationDate)
        assertEquals(2, bank.employees.size)
        assertEquals("Dasha", bank.employees[0].name)
        assertEquals(20, bank.employees[0].age)
        assertEquals("Denis", bank.employees[1].name)
        assertEquals(29, bank.employees[1].age)
    }
}