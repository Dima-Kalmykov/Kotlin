package ru.tinkoff.fintech.database.entities

class Shop(
    val shopId: Int,
    val suppliers: List<Supplier>,
    val visitors: List<Person>
) {

}