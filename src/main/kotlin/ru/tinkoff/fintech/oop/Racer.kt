package ru.tinkoff.fintech.oop

class Racer(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    override var traveledDistance: Int = 0,
    private var clashCount: Int = 0
) : Car {
    override val carType: String
        get() = "Racer"

    override fun run(distance: Int) {
        if (fuelAmount >= 30) {
            traveledDistance += distance
            fuelAmount -= 30
        } else {
            println("Fuel ran out")
        }
    }

    fun makeAccident() {
        ++clashCount
    }

    override fun getInfo(): String {
        return super.getInfo() + ", type = $carType, clashes count = $clashCount"
    }
}