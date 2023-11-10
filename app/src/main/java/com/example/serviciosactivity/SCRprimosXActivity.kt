package com.example.serviciosactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SCRprimosXActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrprimosxactivity)

        findViewById<Button>(R.id.cerrarButton).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.calcularButton).setOnClickListener {
            val numero: Int = findViewById<TextView>(R.id.editTextNumber).text.toString().toIntOrNull() ?: 0

            val intentServicio = Intent(this, PrimosIntentService::class.java).apply {
                putExtra("numero", numero)
            }

            startService(intentServicio)
        }
    }
}
