package com.example.serviciosactivity

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters

class PrimosWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val numero = inputData.getInt("numero", 0)

        val primesList = calcularPrimos(numero)

        val intentResultado = androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(applicationContext)
            .sendBroadcast(Intent(MainActivity.ACTION_RESULTADO).apply {
                putIntegerArrayListExtra("resultadoArray", primesList)
            })

        return Result.success()
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
