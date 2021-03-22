package ru.tinkoff.fintech.database.dao

import ru.tinkoff.fintech.database.entities.Book


data class BookDao(
    val books: List<Book> = listOf(
        Book(1, 100, 200),
        Book(2, 70, 100),
        Book(3, 150, 300),
        Book(4, 300, 200)
    )
) {

    fun getById(bookId: Int): Book {
        return books.firstOrNull() { it.bookId == bookId }
            ?: throw NullPointerException("Invalid book id")
    }
}