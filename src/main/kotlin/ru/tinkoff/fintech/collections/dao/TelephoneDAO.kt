package ru.tinkoff.fintech.collections.dao

import ru.tinkoff.fintech.collections.Telephone

data class TelephoneDAO(
    val telephones: List<Telephone> = listOf(
        Telephone("123", "8-916-345-54-12", "Nokia"),
        Telephone("100", "8-915-627-27-49", "Samsung"),
        Telephone("50", "8-495-612-64-12", "Asus"),
    )
) {

    fun getAll(): List<Telephone> {
        return telephones
    }

    fun getByWiFiNetwork(wiFiNetwork: String): Telephone {
        return telephones.firstOrNull() { it.wiFiNetwork == wiFiNetwork }
            ?: throw NoSuchElementException("There are no telephone connected to the network \"$wiFiNetwork\"")
    }
}