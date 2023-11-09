package com.example.serviciosactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val RESULTCODE: Int = 420

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val primesList = data?.getIntegerArrayListExtra("resultadoArray")
            Log.d("resultadoArray", primesList.toString())
            val textResult: TextView = findViewById(R.id.textResult)
            textResult.text = primesList?.joinToString(", ") ?: "No hay números primos."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonAbrir: Button = findViewById(R.id.buttonAndroidX)

        botonAbrir.setOnClickListener {
            launcher.launch(Intent(this, SCRprimosXActivity::class.java))
        }
    }
}
