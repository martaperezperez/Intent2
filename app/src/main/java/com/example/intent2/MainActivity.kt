package com.example.intent2

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    //RESULTS
    val RESULT_IMAGE_CAPTURE = 1
    val RESULT_ACTIVITY =2

    //Numeros randomgenerados con el metodo randomNumbre()
    val primeroNumero = Random().nextInt(10)
    val segundoNumero = Random().nextInt(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //El boton para iniciar la camara
        val callButton = findViewById<Button>(R.id.camara)
        //El boton para enviar los numeros random
        val buttonRandom = findViewById<Button>(R.id.numerosaleatorios)


        callButton.setOnClickListener {
            //Creamos un intent específico que iniciará la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //Llamamos a la activity de la camara
            startActivityForResult(intent, RESULT_IMAGE_CAPTURE)
        }


        buttonRandom.setOnClickListener {
        //Creamos un intent especifico para iniciar la segunda activity cuando pulsamos el boton de numeros random
        val intent = Intent(this, MainActivity2::class.java)
            //introducimos los numeros random generados por el intent
            intent.putExtra("primerNumero",primeroNumero)
            intent.putExtra("segundoNumero",segundoNumero)
            //iniciamos la actividad con su respectivo result
            startActivityForResult(intent,RESULT_ACTIVITY)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val resultText = findViewById<TextView>(R.id.textView)
        //Si requestCode es distinti de -1
        if (requestCode != Activity.RESULT_OK) {
            when (requestCode) {
                //si requestCode = 1 (CAMARA)
                RESULT_IMAGE_CAPTURE -> {
                    // si data no es null
                    if (data != null) {
                        //recogemos la imagen tomada
                        val imageBitmap = data.extras!!.get("data") as Bitmap
                        //Le mostramos la foto sacada
                        imageView.setImageBitmap(imageBitmap)

                    };
                }
                //Si requestCode = 2 (Resultado de la SecondActivity)
                RESULT_ACTIVITY -> {
                    //Si no es null
                    if (data != null) {
                        //recogemos el dato enviado por la secondActivity
                        val resultString = data.getStringExtra("result")
                        //Sumamos los valores para comprobar el resultado puesto por el usuario
                        val suma = primeroNumero + segundoNumero
                        Log.d("comprobamos", "Resultado 1AC = $resultString")
                        //Comprobamos si la suma es la misma a la dada por el usuario
                        if (suma.toString() == resultString) {
                            resultText.text = "Resultado : CORRECTO "
                        } else {
                            resultText.text = "Resultado: INCORRECTO"
                        }
                    };

                }
                else -> {}
            }
        }

            }
            }



