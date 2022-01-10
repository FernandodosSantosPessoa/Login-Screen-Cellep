package com.example.aulakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            //Chama a LoginActivity
            val mIntent= Intent(this,LoginActivity::class.java)
            // metodo responsavel por executar a Intent
            startActivity(mIntent)
            // metodo responsavel por remover a tela da pilha
           finish()
        }, 3000) // tempo em milisegundos
    }
}