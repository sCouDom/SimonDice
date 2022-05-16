package com.example.simondice

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rojo: Button =findViewById(R.id.rojo)
        val azul: Button =findViewById(R.id.azul)
        val verde: Button =findViewById(R.id.verde)
        val amarillo: Button =findViewById(R.id.amarillo)
        val jugar: Button =findViewById(R.id.jugar)

        var rondas = 1
        var pulsaciones = 0
        var arraySecuencia = ArrayList<Int>()
        var arrayUsuario = ArrayList<Int>()
        var estado = false

        suspend fun iluminarBoton(array: ArrayList<Int>){
            for (color in array){
                when (color){
                    1 ->{
                        rojo.setBackgroundColor(Color.parseColor("#f5a7a2"))
                        val job = CoroutineScope(Dispatchers.Main).launch{
                        }
                        delay(500)
                        rojo.setBackgroundColor(Color.parseColor("#E82929"))
                        delay(300)
                    }
                    2 ->{
                        azul.setBackgroundColor(Color.parseColor("#a2adfa"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                        }
                        delay(500)
                        azul.setBackgroundColor(Color.parseColor("#0F2DD3"))
                        delay(300)
                    }
                    3->{
                        amarillo.setBackgroundColor(Color.parseColor("#fafaac"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                        }
                        delay(500)
                        amarillo.setBackgroundColor(Color.parseColor("#EDDB41"))
                        delay(300)
                    }
                    4->{
                        verde.setBackgroundColor(Color.parseColor("#bfeda1"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                        }
                        delay(500)
                        verde.setBackgroundColor(Color.parseColor("#5FCC1A"))
                        delay(300)
                    }
                }
            }
        }

        fun iluminarBotones(array: ArrayList<Int>){
            val job = CoroutineScope(Dispatchers.Main).launch {
                iluminarBoton(array)
            }
        }

        suspend fun ciclo() {
            if (arraySecuencia == arrayUsuario) {
                rondas++
                arrayUsuario.clear()
                arraySecuencia.add(Random.nextInt(4) + 1)
                delay(500)
                iluminarBotones(arraySecuencia)
                pulsaciones = 0
            } else {
                Toast.makeText(applicationContext, "Incorrecto.", Toast.LENGTH_SHORT).show()
                estado = false
            }
        }

        jugar.setOnClickListener(){
            rondas = 1
            Toast.makeText(applicationContext, "Comienza el juego.", Toast.LENGTH_SHORT).show()
            arraySecuencia.clear()
            arrayUsuario.clear()
            estado = true
            arraySecuencia.add(Random.nextInt(4)+1)
            iluminarBotones(arraySecuencia)
            pulsaciones = 0
        }

        fun secuenciaColores(array: ArrayList<Int>, color: Int){
            array.add(color)
        }

        fun juego(color: Int){
            secuenciaColores(arrayUsuario,color)
            pulsaciones++
            if (rondas == pulsaciones){
                val job = CoroutineScope(Dispatchers.Main).launch{
                    ciclo()
                }
            }
        }

        rojo.setOnClickListener(){
            juego(1)
        }

        azul.setOnClickListener(){
            juego(2)
        }

        amarillo.setOnClickListener(){
            juego(3)
        }

        verde.setOnClickListener(){
            juego(4)
        }
    }}