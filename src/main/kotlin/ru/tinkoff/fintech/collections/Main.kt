package ru.tinkoff.fintech.collections

fun main() {
    val machineManager = MachineManager()
    machineManager.fillList()

    val count = machineManager.getCountByPredicate { it.number.contains('5') }
    println("The quantity of phone numbers which contain digit \"5\": $count")

    println("\nWiFi network tree:")
    val grouping = machineManager.groupByWiFiNetwork()
    for ((key, groupContent) in grouping) {
        println("\n$key:")
        for (item in groupContent) {
            println("    $item")
        }
    }

    println("\nList sorted by screen width:\n")
    for (item in machineManager.getSortedList(compareBy{it.screenWidth})) {
        println(item)
    }
}
