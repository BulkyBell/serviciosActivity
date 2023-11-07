package com.example.serviciosactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SCRprimosXActivity : AppCompatActivity() {

    private var elementos=ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrprimosxactivity)

        findViewById<Button>(R.id.cerrarButton).setOnClickListener{
            finish()
        }

        findViewById<Button>(R.id.calcularButton).setOnClickListener{
            var numero: Int = Integer.parseInt(findViewById<TextView>(R.id.editTextNumber).text.toString())
            var intentResultao = Intent(this, MainActivity::class.java)
            intentResultao.putIntegerArrayListExtra("resultadoArray",  calcularPrimos(numero))
            println(elementos.toString())
            setResult(RESULT_OK, intentResultao)
            finish()
        }


    }

    private fun calcularPrimos(n:Int):ArrayList<Int>{
        for (i in 2 .. n){
            if (calcularPrimos(i, i-1))
                elementos.add(i)
        }
        return elementos
    }

    private fun calcularPrimos(num:Int, divisor: Int):Boolean{
        if (divisor==1){
            return true
        }
        else if (num%divisor==0){
            return false
        }
        else{
            return calcularPrimos(num, divisor-1)
        }
    }

}