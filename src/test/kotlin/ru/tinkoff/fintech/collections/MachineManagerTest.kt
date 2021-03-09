package ru.tinkoff.fintech.collections

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@DisplayName("Machine manager test")
internal class MachineManagerTest {

    private val machineManager: MachineManager = MachineManager()

    private fun equalComputerWithPhone(
        computerWithPhone1: ComputerWithPhone,
        computerWithPhone2: ComputerWithPhone
    ): Boolean {
        return computerWithPhone1.number == computerWithPhone2.number
                && computerWithPhone1.wiFiNetwork == computerWithPhone2.wiFiNetwork
                && computerWithPhone1.model == computerWithPhone2.model
                && computerWithPhone1.screenHeight == computerWithPhone2.screenHeight
                && computerWithPhone1.screenWidth == computerWithPhone2.screenWidth
    }

    @Test
    @DisplayName("Sorted list default")
    fun getSortedListDefault() {
        machineManager.fillList()

        val expected = listOf(
            ComputerWithPhone("100", 14, 15, "8-915-627-27-49", "Samsung"),
            ComputerWithPhone("123", 12, 13, "8-916-345-54-12", "Nokia"),
            ComputerWithPhone("50", 13, 15, "8-495-612-64-12", "Asus")
        )

        val default = machineManager.getSortedList()

        assertTrue(equalComputerWithPhone(expected[0], default[0]))
        assertTrue(equalComputerWithPhone(expected[1], default[1]))
        assertTrue(equalComputerWithPhone(expected[2], default[2]))
    }

    @Test
    @DisplayName("Sorted list with custom comparator")
    fun getSortedList() {
        machineManager.fillList()

        val expected = listOf(
            ComputerWithPhone("123", 12, 13, "8-916-345-54-12", "Nokia"),
            ComputerWithPhone("50", 13, 15, "8-495-612-64-12", "Asus"),
            ComputerWithPhone("100", 14, 15, "8-915-627-27-49", "Samsung")
        )

        val default = machineManager.getSortedList(compareBy { it.screenWidth })

        assertTrue(equalComputerWithPhone(expected[0], default[0]))
        assertTrue(equalComputerWithPhone(expected[1], default[1]))
        assertTrue(equalComputerWithPhone(expected[2], default[2]))
    }

    @Test
    @DisplayName("Test groupBy")
    fun groupByWiFiNetwork() {
        machineManager.fillList()

        val expected = listOf(
            ComputerWithPhone("50", 13, 15, "8-495-612-64-12", "Asus"),
            ComputerWithPhone("100", 14, 15, "8-915-627-27-49", "Samsung"),
            ComputerWithPhone("123", 12, 13, "8-916-345-54-12", "Nokia")
        )

        val grouping = machineManager.groupByWiFiNetwork()

        for ((key, groupContent) in grouping) {
            assertEquals(1, groupContent.size)

            when (key) {
                "50" -> assertTrue(equalComputerWithPhone(expected[0], groupContent[0]))
                "100" -> assertTrue(equalComputerWithPhone(expected[1], groupContent[0]))
                "123" -> assertTrue(equalComputerWithPhone(expected[2], groupContent[0]))
            }
        }
    }

    @Test
    @DisplayName("Count by predicate")
    fun getCountByPredicate() {
        machineManager.fillList()

        assertEquals(2, machineManager.getCountByPredicate { it.screenWidth > 12 })
        assertEquals(1, machineManager.getCountByPredicate { it.screenWidth > 11 && it.screenHeight< 15 })
        assertEquals(3, machineManager.getCountByPredicate { it.number.startsWith("8") })
        assertEquals(0, machineManager.getCountByPredicate { it.screenHeight > 15 })
    }
}
