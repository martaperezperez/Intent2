package com.example.intent2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //declaramos el intent
        val intent = getIntent()
        //reogemos los valores enviados de la MainActivity
        val primeroNumero = intent.getIntExtra("primerNumero", 0)
        val segundoNumero = intent.getIntExtra("segundoNumero", 0)

        //los enviamos al TextView para que el usuario pueda verlos
        val sumaText = findViewById<TextView>(R.id.textView2)
        sumaText.text= "$primeroNumero + $segundoNumero = ?"

        val sendButton = findViewById<Button>(R.id.button)
        val resultEditText = findViewById<EditText>(R.id.editTextNumber)

        sendButton.setOnClickListener{
            // metemos el dato resultado en el intent que recogemos del EdiText
            intent.putExtra("result",resultEditText.text.toString())

            Log.d("CHECHK","RESULTADO = ${resultEditText}")
            //Enviamos los datos
            setResult(Activity.RESULT_OK, intent)
            //Cerramos
            finish()

        }
    }

}