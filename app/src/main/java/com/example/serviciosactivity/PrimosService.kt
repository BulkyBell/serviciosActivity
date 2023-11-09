package com.example.serviciosactivity

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrimosService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val numero = intent?.getIntExtra("numero", 0) ?: 0

        GlobalScope.launch(Dispatchers.Default) {
            val primesList = calcularPrimos(numero)

            val intentResultado = Intent().apply {
                action = MainActivity.ACTION_RESULTADO
                putIntegerArrayListExtra("resultadoArray", primesList)
            }

            sendBroadcast(intentResultado)
            stopSelf()
        }

        return START_NOT_STICKY
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
