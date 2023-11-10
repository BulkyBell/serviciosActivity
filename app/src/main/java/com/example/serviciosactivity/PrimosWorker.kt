package com.example.serviciosactivity

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.eventbus.EventBus

class PrimosWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val numero = inputData.getInt("numero", 0)

        val primesList = calcularPrimos(numero)

        // Publicar un evento utilizando EventBus
        EventBus.getDefault().post(PrimosEvent(primesList))

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

class PrimosEvent(val primesList: ArrayList<Int>?)
