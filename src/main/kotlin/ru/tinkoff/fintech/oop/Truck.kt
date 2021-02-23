package ru.tinkoff.fintech.oop

class Truck(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    override var traveledDistance: Int,
    private val capacity : Int
) : Car {
    override val carType: String
        get() = "Truck"

    override fun run(distance: Int) {
        fuelAmount -= 15
        traveledDistance += distance
    }

    fun deliverThings() {
        println("Truck delivers things to destination")
    }

    override fun getInfo(): String {
        return super.getInfo() + ", type = $carType, capacity = $capacity"
    }

}