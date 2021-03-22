package ru.tinkoff.fintech.database.dao

import ru.tinkoff.fintech.database.entities.Shop

data class ShopDao(
    val shops: List<Shop> = listOf(
        Shop(
            1,
            listOf(
                SupplierDao().getById(1),
                SupplierDao().getById(2)
            ),
            listOf(
                PersonDao().getById(1),
                PersonDao().getById(2)
            )
        ),
        Shop(
            2,
            listOf(
                SupplierDao().getById(3),
                SupplierDao().getById(4)
            ),
            listOf(
                PersonDao().getById(1),
                PersonDao().getById(3)
            )
        ),
    )
) {

    fun getAll(): List<Shop> {
        return shops
    }
}