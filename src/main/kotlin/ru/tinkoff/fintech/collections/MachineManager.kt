package ru.tinkoff.fintech.collections

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataProcessingException
import ru.tinkoff.fintech.collections.dao.ComputerDAO
import ru.tinkoff.fintech.collections.dao.TelephoneDAO

class MachineManager {

    private var computerWithPhoneList: List<ComputerWithPhone> = emptyList()

    fun fillList() {
        val phones = TelephoneDAO().getAll()

        computerWithPhoneList = phones.map { phone ->

            val computer = ComputerDAO().getByWiFiNetwork(phone.wiFiNetwork)

            ensureEqualWiFiNetworks(phone, computer)

            ComputerWithPhone(
                phone.wiFiNetwork,
                computer.screenWidth,
                computer.screenHeight,
                phone.number,
                phone.model
            )
        }
    }

    fun getSortedList(comparator: Comparator<ComputerWithPhone> = compareBy { it.wiFiNetwork }): List<ComputerWithPhone> {
        return computerWithPhoneList.sortedWith(comparator)
    }

    fun groupByWiFiNetwork(): Map<String, List<ComputerWithPhone>> {
        return computerWithPhoneList.groupBy { it.wiFiNetwork }
    }

    fun getCountByPredicate(predicate: (ComputerWithPhone) -> Boolean): Int {
        return computerWithPhoneList.count(predicate)
    }

    private fun ensureEqualWiFiNetworks(phone: Telephone, computer: Computer) {
        if (phone.wiFiNetwork != computer.wiFiNetwork) {
            throw DataProcessingException(
                "Your devices are connected to different networks:\n" +
                        "Phone: \"${phone.wiFiNetwork}\"\n" +
                        "Computer: \"${computer.wiFiNetwork}\""
            )
        }
    }
}