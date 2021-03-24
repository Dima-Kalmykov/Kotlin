package ru.tinkoff.fintech.mock

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmployeeDaoTest {

    @Test
    fun `bank 'getAll' method`() {
        val employeeDao = mockk<EmployeeDao> {
            every { getAll() } returns listOf(
                mockk {
                    every { name } returns "Nastya"
                    every { age } returns 20
                    every { id } returns 0
                },
                mockk {
                    every { name } returns "Vasya"
                    every { age } returns 22
                    every { id } returns 1
                },
                mockk {
                    every { name } returns "Kolya"
                    every { age } returns 27
                    every { id } returns 2
                },
                mockk {
                    every { name } returns "Polina"
                    every { age } returns 28
                    every { id } returns 3
                },
                mockk {
                    every { name } returns "Petya"
                    every { age } returns 22
                    every { id } returns 4
                }
            )
        }

        Assertions.assertAll(
            "'getAll' returns correct list",
            { Assertions.assertEquals(5, employeeDao.getAll().size) },
            { Assertions.assertEquals("Nastya", employeeDao.getAll()[0].name) },
            { Assertions.assertEquals(20, employeeDao.getAll()[0].age) },
            { Assertions.assertEquals(0, employeeDao.getAll()[0].id) },
            { Assertions.assertEquals("Vasya", employeeDao.getAll()[1].name) },
            { Assertions.assertEquals(22, employeeDao.getAll()[1].age) },
            { Assertions.assertEquals(1, employeeDao.getAll()[1].id) },
            { Assertions.assertEquals("Kolya", employeeDao.getAll()[2].name) },
            { Assertions.assertEquals(27, employeeDao.getAll()[2].age) },
            { Assertions.assertEquals(2, employeeDao.getAll()[2].id) },
            { Assertions.assertEquals("Polina", employeeDao.getAll()[3].name) },
            { Assertions.assertEquals(28, employeeDao.getAll()[3].age) },
            { Assertions.assertEquals(3, employeeDao.getAll()[3].id) },
            { Assertions.assertEquals("Petya", employeeDao.getAll()[4].name) },
            { Assertions.assertEquals(22, employeeDao.getAll()[4].age) },
            { Assertions.assertEquals(4, employeeDao.getAll()[4].id) },
        )
        verify(exactly = 16) { employeeDao.getAll() }
    }

    @Test
    fun `bank 'getById' method returns not null`() {
        val employeeDao = mockk<EmployeeDao> {
            every { getById(0) } returns Employee("Nastya", 20, 0)
            every { getById(1) } returns Employee("Vasya", 22, 1)
            every { getById(2) } returns Employee("Kolya", 27, 2)
            every { getById(3) } returns Employee("Polina", 28, 3)
            every { getById(4) } returns Employee("Petya", 22, 4)
        }

        Assertions.assertAll(
            "'getById' returns appropriate person",
            { Assertions.assertNotNull(employeeDao.getById(0)) },
            { Assertions.assertEquals("Nastya", employeeDao.getById(0)?.name) },
            { Assertions.assertEquals(20, employeeDao.getById(0)?.age) },
            { Assertions.assertEquals(0, employeeDao.getById(0)?.id) },
            { Assertions.assertNotNull(employeeDao.getById(1)) },
            { Assertions.assertEquals("Vasya", employeeDao.getById(1)?.name) },
            { Assertions.assertEquals(22, employeeDao.getById(1)?.age) },
            { Assertions.assertEquals(1, employeeDao.getById(1)?.id) },
            { Assertions.assertNotNull(employeeDao.getById(2)) },
            { Assertions.assertEquals("Kolya", employeeDao.getById(2)?.name) },
            { Assertions.assertEquals(27, employeeDao.getById(2)?.age) },
            { Assertions.assertEquals(2, employeeDao.getById(2)?.id) },
            { Assertions.assertNotNull(employeeDao.getById(3)) },
            { Assertions.assertEquals("Polina", employeeDao.getById(3)?.name) },
            { Assertions.assertEquals(28, employeeDao.getById(3)?.age) },
            { Assertions.assertEquals(3, employeeDao.getById(3)?.id) },
            { Assertions.assertNotNull(employeeDao.getById(4)) },
            { Assertions.assertEquals("Petya", employeeDao.getById(4)?.name) },
            { Assertions.assertEquals(22, employeeDao.getById(4)?.age) },
            { Assertions.assertEquals(4, employeeDao.getById(4)?.id) },
        )
    }

    @Test
    fun `bank 'getById' method returns null`() {
        val employeeDao = mockk<EmployeeDao> {
            every { getById(or(more(5, true), less(0))) } returns null
        }

        Assertions.assertAll(
            "'getById' returns null",
            { Assertions.assertNull(employeeDao.getById(-1)) },
            { Assertions.assertNull(employeeDao.getById(-2)) },
            { Assertions.assertNull(employeeDao.getById(5)) },
            { Assertions.assertNull(employeeDao.getById(10)) },
            { Assertions.assertNull(employeeDao.getById(10000)) },
            { Assertions.assertNull(employeeDao.getById(-9999)) },
        )

        for (id in listOf(-1, -2, 5, 10, 10000, -9999)) {
            verify(exactly = 1) { employeeDao.getById(id) }
        }
    }
}