package com.example.serviciosactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private val RESULTCODE: Int = 420
    private val receiver: ResultadoReceiver = ResultadoReceiver()

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val primesList = data?.getIntegerArrayListExtra("resultadoArray")
            displayResult(primesList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonAbrir: Button = findViewById(R.id.buttonAndroidX)

        botonAbrir.setOnClickListener {
            launcher.launch(Intent(this, SCRprimosXActivity::class.java))
        }

        val filter = IntentFilter(ACTION_RESULTADO)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    private fun displayResult(primesList: ArrayList<Int>?) {
        Log.d("resultadoArray", primesList.toString())
        val textResult: TextView = findViewById(R.id.textResult)
        textResult.text = primesList?.joinToString(", ") ?: "No hay números primos."
    }

    inner class ResultadoReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val primesList = intent?.getIntegerArrayListExtra("resultadoArray")
            displayResult(primesList)
        }
    }

    companion object {
        const val ACTION_RESULTADO = "com.example.serviciosactivity.ACTION_RESULTADO"
    }
}
