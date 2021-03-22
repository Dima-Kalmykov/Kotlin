package ru.tinkoff.fintech.database.dao

import ru.tinkoff.fintech.database.entities.Supplier

data class SupplierDao(
    val suppliers: List<Supplier> = listOf(
        Supplier(1, "Samsung", 20),
        Supplier(2, "Nokia", 34),
        Supplier(3, "Apple", 33),
        Supplier(4, "Asus", 28),
    )
) {

    fun getById(supplierId: Int): Supplier {
        return suppliers.firstOrNull() { it.supplierId == supplierId }
            ?: throw NullPointerException("Invalid supplier id")
    }
}