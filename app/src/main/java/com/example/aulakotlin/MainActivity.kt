package com.example.aulakotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.aulakotlin.databinding.ActivityLoginBinding
import com.example.aulakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //recuperar email passado entre as intents
        val email= intent.getStringExtra("INTENT_EMAIL")
        //acessar o arquivo de preferencias compartilhadas
        val sharedPrefs= getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        //recuperar dados do arquivo de preferencias compartilhadas
        val nome= sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val continente = sharedPrefs.getString("CONTINENTE", "")
        //exibir dados na tela
        binding.txvMainNome.text="$nome $sobrenome"
        binding.txvMainEmail.text= email
        binding.txvMainContinente.text= continente

        //escutando o click do botao sair
        binding.btnMainSair.setOnClickListener{

            //criando uma caixinha de diálogo
            val alert= AlertDialog.Builder(this)

            //definindo título e mensagem na caixa de diálogo
            alert.setTitle("Atenção!")
            alert.setMessage("Deseja realmente sair?")

            //definir rótulo do botao

            alert.setPositiveButton("Sair"){ dialog,which ->
                val myIntent= Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
                finishAffinity()
            }
            alert.setNeutralButton("Não"){dialog,which ->}

            alert.setCancelable(false)

            //exibe a caixa de alert pro usuário
            alert.show()

        }
        //escutando o click do botão do site
        binding.btnMainSite.setOnClickListener{
            val mIntent= Intent(this, webActivity::class.java)
            startActivity(mIntent)
        }
    }
}