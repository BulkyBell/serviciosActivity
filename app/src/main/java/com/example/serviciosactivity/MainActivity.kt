package com.example.serviciosactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val RESULTCODE: Int = 420


    var launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val intent = it.data
        Log.d("resultadoArray", it.resultCode.toString())
        Log.d("resultadoArray", it.data?.getStringExtra("ejemplo").toString())
        var textResult: TextView = findViewById<TextView>(R.id.textResult)
        textResult.text = intent?.getIntegerArrayListExtra("resultadoArray").toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var botonAbrir: Button = findViewById<Button>(R.id.buttonAndroidX)

        botonAbrir.setOnClickListener {
            launcher.launch(Intent(this, SCRprimosXActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var textResult: TextView = findViewById<TextView>(R.id.textResult)
        textResult.text= data?.getIntegerArrayListExtra("resultadoArray").toString()
    }
}
