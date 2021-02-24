package ru.tinkoff.fintech.oop

class Truck(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    private val capacity: Int,
    override var traveledDistance: Int = 0,
) : Car {
    override val carType: String
        get() = "Truck"

    override fun run(distance: Int) {
        if (fuelAmount >= 15) {
            fuelAmount -= 15
            traveledDistance += distance
        } else {
            println("Fuel ran out")
        }
    }

    fun deliverThings() {
        println("Truck delivers things to destination")
    }

    fun deliverThings(product: Any) {
        println("Truck delivers $product to destination")
    }

    override fun getInfo(): String {
        return super.getInfo() + ", type = $carType, capacity = $capacity"
    }

}