fun main() {
    println(countFee(payType = "Maestro", previousTransfers = 75_000_00, currentTransfer = 10_000_00))
}

fun countFee(payType: String = "VK Pay", previousTransfers: Int = 0, currentTransfer: Int): Int {
    val feeInPercentVisaMir = 0.0075
    val feeInPercentMasterMaestro = 0.006
    val minimalFeeVisaMir = 3500
    val floatingFeeVisaMir = (currentTransfer * feeInPercentVisaMir).toInt()
    val floatingFeeMasterMaestro = (currentTransfer * feeInPercentMasterMaestro).toInt() + 2000

    return when (payType) {
        "VK Pay" -> 0
        "Visa", "Мир" -> if (floatingFeeVisaMir <= minimalFeeVisaMir) minimalFeeVisaMir else floatingFeeVisaMir
        "MasterCard", "Maestro" -> if (previousTransfers < 75_000_00) 0 else floatingFeeMasterMaestro
        else -> throw Exception("Такого способа оплаты не существует")
    }
}