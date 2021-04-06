package ru.tinkoff.fintech.coroutines.task1.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.tinkoff.fintech.coroutines.task1.book_info.AdditionalBookInfo
import ru.tinkoff.fintech.coroutines.task1.book_info.FullBookInfo
import ru.tinkoff.fintech.coroutines.task1.book_info.MainBookInfo
import ru.tinkoff.fintech.coroutines.task1.dao.AdditionalInfoDAO
import ru.tinkoff.fintech.coroutines.task1.dao.MainInfoDAO

class DataGetter {

    fun start() {
        val addDao = AdditionalInfoDAO()
        val mainDao = MainInfoDAO()
        val service = MainService()

        runWithoutBlockingAdditionalInfo(addDao, mainDao, service)
        println()
        runWithBlockingAdditionalInfo(addDao, mainDao, service)
    }

    private fun runWithoutBlockingAdditionalInfo(
        addDao: AdditionalInfoDAO,
        mainDao: MainInfoDAO,
        service: MainService
    ) {
        var mainBook1 = MainBookInfo("", "")
        var addBook1: AdditionalBookInfo? = null
        var mainBook2 = MainBookInfo("", "")
        var addBook2: AdditionalBookInfo? = null
        var mainBook3 = MainBookInfo("", "")
        var addBook3: AdditionalBookInfo? = null
        runBlocking {
            launch {
                mainBook1 = mainDao.getByTitle("100 rings")
                mainBook2 = mainDao.getByTitle("A passage to India")
                mainBook3 = mainDao.getByTitle("War and peace")
            }
        }

        GlobalScope.launch {
            addBook1 = addDao.getByTitle("100 rings")
            addBook2 = addDao.getByTitle("A passage to India")
            addBook3 = addDao.getByTitle("War and peace")
        }

        val list: List<FullBookInfo> = listOf(
            service.getFullInfo(mainBook1, addBook1),
            service.getFullInfo(mainBook2, addBook2),
            service.getFullInfo(mainBook3, addBook3),
        )

        for (i in list.indices) {
            println("Without blocking \"Additional info\" Book$i = ${list[i]}")
        }
    }

    private fun runWithBlockingAdditionalInfo(
        addDao: AdditionalInfoDAO,
        mainDao: MainInfoDAO,
        service: MainService
    ) {
        var mainBook1 = MainBookInfo("", "")
        var addBook1: AdditionalBookInfo? = null
        var mainBook2 = MainBookInfo("", "")
        var addBook2: AdditionalBookInfo? = null
        var mainBook3 = MainBookInfo("", "")
        var addBook3: AdditionalBookInfo? = null
        runBlocking {
            launch {
                mainBook1 = mainDao.getByTitle("100 rings")
                mainBook2 = mainDao.getByTitle("A passage to India")
                mainBook3 = mainDao.getByTitle("War and peace")
            }

            launch {
                addBook1 = addDao.getByTitle("100 rings")
                addBook2 = addDao.getByTitle("A passage to India")
                addBook3 = addDao.getByTitle("War and peace")
            }
        }

        val list: List<FullBookInfo> = listOf(
            service.getFullInfo(mainBook1, addBook1),
            service.getFullInfo(mainBook2, addBook2),
            service.getFullInfo(mainBook3, addBook3),
        )

        for (i in list.indices) {
            println("With blocking \"Additional info\" Book$i = ${list[i]}")
        }
    }
}