package com.example.serviciosactivity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private val RESULTCODE: Int = 420
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
            launcher.launch(SCRprimosXActivity::class.java)
        }

        // Registrar EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Desregistrar EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    private fun displayResult(primesList: ArrayList<Int>?) {
        Log.d("resultadoArray", primesList.toString())
        val textResult: TextView = findViewById(R.id.textResult)
        textResult.text = primesList?.joinToString(", ") ?: "No hay números primos."
    }

    // Manejar el evento de EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPrimosEvent(event: PrimosEvent) {
        displayResult(event.primesList)
    }

    companion object {
        const val ACTION_RESULTADO = "com.example.serviciosactivity.ACTION_RESULTADO"
    }
}
