package com.example.serviciosactivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf

class SCRprimosXActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrprimosxactivity)

        findViewById<Button>(R.id.cerrarButton).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.calcularButton).setOnClickListener {
            val numero: Int =
                findViewById<TextView>(R.id.editTextNumber).text.toString().toIntOrNull() ?: 0

            val workRequest = OneTimeWorkRequest.Builder(PrimosWorker::class.java)
                .setInputData(workDataOf("numero" to numero))
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}
