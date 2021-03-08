package ru.tinkoff.fintech.collections.dao

import ru.tinkoff.fintech.collections.Computer

data class ComputerDAO(
    val computers: List<Computer> = listOf(
        Computer("123", 12, 13),
        Computer("100", 14, 15),
        Computer("50", 13, 15)
    )
) {

    fun getAll(): List<Computer> {
        return computers
    }

    fun getByWiFiNetwork(wiFiNetwork: String): Computer {
        return computers.firstOrNull() { it.wiFiNetwork == wiFiNetwork }
            ?: throw NoSuchElementException("There are no computer connected to the network \"$wiFiNetwork\"")
    }
}