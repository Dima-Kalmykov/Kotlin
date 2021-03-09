package ru.tinkoff.fintech.collections

data class ComputerWithPhone(
    val wiFiNetwork: String,
    val screenWidth: Int,
    val screenHeight: Int,
    val number: String,
    val model: String
) {
    override fun toString(): String {
        return "model = $model, number = $number, size = $screenWidth x $screenHeight"
    }
}
