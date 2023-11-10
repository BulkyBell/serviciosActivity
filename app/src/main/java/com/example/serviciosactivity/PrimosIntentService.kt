package com.example.serviciosactivity

import android.app.IntentService
import android.content.Intent
import java.util.*

class PrimosIntentService : IntentService("PrimosIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val numero = intent?.getIntExtra("numero", 0) ?: 0

        val primesList = calcularPrimos(numero)

        val intentResultado = Intent().apply {
            action = MainActivity.ACTION_RESULTADO
            putIntegerArrayListExtra("resultadoArray", primesList)
        }

        sendBroadcast(intentResultado)
    }

    private fun calcularPrimos(n: Int): ArrayList<Int> {
        val elementos = ArrayList<Int>()
        for (i in 2..n) {
            if (calcularPrimos(i, i - 1)) {
                elementos.add(i)
            }
        }
        return elementos
    }

    private fun calcularPrimos(num: Int, divisor: Int): Boolean {
        return when {
            divisor == 1 -> true
            num % divisor == 0 -> false
            else -> calcularPrimos(num, divisor - 1)
        }
    }
}
