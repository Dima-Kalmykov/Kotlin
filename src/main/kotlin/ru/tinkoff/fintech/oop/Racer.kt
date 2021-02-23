package ru.tinkoff.fintech.oop

class Racer(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    override var traveledDistance: Int,
    private var clashCount : Int
) : Car {
    override val carType: String
        get() = "Racer"

    override fun run(distance: Int) {
        traveledDistance += distance
        fuelAmount -= 30
    }

    fun makeAccident() {
        ++clashCount
    }

    override fun getInfo(): String {
        return super.getInfo() + ", type = $carType, clashes count = $clashCount"
    }
}