package ru.tinkoff.fintech.database.utils

class Service(private val client: Client) {

    /**
     * Select by id.
     */
    fun findOneItem() {
        val result = client.getById("SELECT * FROM book WHERE book_id=", 1)
        if (result != null) {
            while (result.next()) {
                val pageAmount = result.getInt("page_amount")
                val rating = result.getInt("rating")
                println("Book with id=1 found: book rating = $rating, page amount = $pageAmount")
            }
        }
    }

    /**
     * Find some persons by predicate.
     */
    fun findByPredicate() {
        val result = client.executeSql("SELECT * FROM supplier WHERE age>25")
        if (result != null) {
            println("\nAge > 25: ")
            while (result.next()) {
                val supplierId = result.getInt("supplier_id")
                val age = result.getInt("age")
                println("Supplier id = $supplierId, age = $age")
            }
        }
    }

    /**
     * Example of using "GROUP BY" function in sql.
     */
    fun groupBy() {
        println()

        val result = client.executeSql("SELECT COUNT(shop_id), person_id FROM supplier GROUP BY person_id")
        if (result != null) {
            var groupsAmount = 0
            while (result.next()) {
                val personId = result.getInt("person_id")

                println("Person id = $personId")
                ++groupsAmount
            }
            println("\nTotal count of groups (by person id) = $groupsAmount")

        }
    }

    /**
     * Sort books by rating.
     */
    fun orderBy() {
        println("\nBooks, sorted by rating:\n")
        val result = client.executeSql("SELECT * FROM book ORDER BY rating")
        if (result != null) {
            while (result.next()) {
                val bookId = result.getInt("book_id")
                val pageAmount = result.getInt("page_amount")
                val rating = result.getInt("rating")
                println("Book id = $bookId, page amount = $pageAmount, rating = $rating")
            }
        }
    }

    /**
     * Join 2 tables.
     */
    fun join() {
        println("\nJoin tables:\n")
        val result = client.executeSql(
            "SELECT * FROM book LEFT JOIN supplier ON book.person_id=supplier.person_id"
        )
        if (result != null) {
            while (result.next()) {
                val bookId = result.getInt("book_id")
                val personId = result.getInt("person_id")
                val pageAmount = result.getInt("page_amount")
                val rating = result.getInt("rating")
                val supplierId = result.getInt("supplier_id")
                val shopId = result.getInt("shop_id")
                val companyName = result.getString("company_name")
                val age = result.getInt("age")

                println(
                    "Book id =$bookId, person id = $personId, page amount = $pageAmount," +
                            " rating = $rating, supplier id = $supplierId, shop id = $shopId," +
                            " company name = $companyName, age = $age"
                )
            }
        }
    }
}