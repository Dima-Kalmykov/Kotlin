package ru.tinkoff.fintech.oop

interface Car {

    val carType: String

    val fuelAmount: Int

    val maxSpeed: Int

    val traveledDistance: Int

    fun run(distance: Int)

    fun getInfo() : String {
        return "Car type = $carType, fuel amount = $fuelAmount," +
                " max speed = $maxSpeed, total distance = $traveledDistance"
    }
}