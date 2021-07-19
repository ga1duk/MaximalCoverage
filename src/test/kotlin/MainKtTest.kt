import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class MainKtTest {

    @Test
    fun countFee_VKPay() {
        val payType = "VK Pay"
        val previousTransfers = 100_000_00
        val currentTransfer = 15_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun countFee_DefaultWithOneAttribute() {
        val currentTransfer = 15_000_00

        val result = countFee(
            currentTransfer = currentTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun countFee_Default() {
        val result = countFee()

        assertEquals(100, result)
    }

    @Test
    fun countFee_VisaMinimalCommission() {
        val payType = "Visa"
        val previousTransfers = 0
        val currentTransfer = 100_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(3500, result)
    }

    @Test
    fun countFee_MirMinimalCommission() {
        val payType = "Мир"
        val previousTransfers = 0
        val currentTransfer = 1000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(3500, result)
    }

    @Test
    fun countFee_VisaFloatingCommission() {
        val payType = "Visa"
        val previousTransfers = 0
        val currentTransfer = 10_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(7500, result)
    }

    @Test
    fun countFee_MirFloatingCommission() {
        val payType = "Мир"
        val previousTransfers = 0
        val currentTransfer = 10_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(7500, result)
    }

    @Test
    fun countFee_MasterCardFloatingCommission() {
        val payType = "MasterCard"
        val previousTransfers = 75_000_00
        val currentTransfer = 10_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(8000, result)
    }

    @Test
    fun countFee_MasterCardZeroCommission() {
        val payType = "MasterCard"
        val previousTransfers = 5_000_00
        val currentTransfer = 150_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun countFee_MaestroFloatingCommission() {
        val payType = "Maestro"
        val previousTransfers = 75_000_00
        val currentTransfer = 10_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(8000, result)
    }

    @Test
    fun countFee_MaestroZeroCommission() {
        val payType = "Maestro"
        val previousTransfers = 5_000_00
        val currentTransfer = 150_000_00

        val result = countFee(
            payType = payType,
            previousTransfers = previousTransfers,
            currentTransfer = currentTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun countFee_InvalidPayTypeArgument() {
        val payType = "Maeestro"
        val previousTransfers = 5_000_00
        val currentTransfer = 150_000_00

        val exception = assertThrows(Exception::class.java) {
            countFee(
                payType = payType,
                previousTransfers = previousTransfers,
                currentTransfer = currentTransfer
            )
        }
        assertEquals("Такого способа оплаты не существует", exception.message)
    }
}