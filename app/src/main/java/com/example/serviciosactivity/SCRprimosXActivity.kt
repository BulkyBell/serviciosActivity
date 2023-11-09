package com.example.serviciosactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SCRprimosXActivity : AppCompatActivity() {

    private val elementos = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrprimosxactivity)

        findViewById<Button>(R.id.cerrarButton).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.calcularButton).setOnClickListener {
            val numero: Int = findViewById<TextView>(R.id.editTextNumber).text.toString().toIntOrNull() ?: 0
            val primesList = calcularPrimos(numero)

            val intentResultado = Intent().apply {
                putIntegerArrayListExtra("resultadoArray", primesList)
            }

            setResult(RESULT_OK, intentResultado)
            finish()
        }
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
