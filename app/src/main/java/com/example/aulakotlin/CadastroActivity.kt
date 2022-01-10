package com.example.aulakotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.aulakotlin.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //criar uma lista de opções para o spinner
        val listaContinentes= arrayListOf("Continente", "África", "Antartida", "América", "Ásia", "Europa", "Oceania")

        //criando um adaptador para o spinner
        val spinnerAdapter= ArrayAdapter(
            this,                                           //contexto
            android.R.layout.simple_spinner_dropdown_item,         //layout
            listaContinentes                                       //dados
        )
        binding.spnCadastroContinente.adapter= spinnerAdapter

        //quando o botão cadastrar for clicado faça
        binding.btnCadastroCadastrar.setOnClickListener {
            //os dados são capturados e armazenados em variáveis
            val nome= binding.edtCadastroName.text.toString().trim()
            val sobrenome= binding.edtCadastroSobrenome.text.toString().trim()
            val email= binding.edtCadastroEmail.text.toString().trim()
            val senha= binding.edtCadastroSenha.text.toString().trim()
            val continente= binding.spnCadastroContinente.selectedItem.toString()

            //verifica se os campos estão vazios
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty()|| senha.isEmpty()
                || continente == listaContinentes[0]) {
                // se qualquer campo estiver vazio uma mensagem de erro será mostrada
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                // criando uma referencia à um arquivo de preferencias compartilhadas
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email", Context.MODE_PRIVATE
                )
                //tornar a referencia editável
                val editPrefs = sharedPrefs.edit()

                //preparar os dados para serem salvos na planilha
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("CONTINENTE", continente)

                //salvar dados no arquivo
                editPrefs.apply()

                val mIntent= Intent(this, MainActivity::class.java)
                mIntent.putExtra("INTENT_EMAIL", email)

                startActivity(mIntent)
                //método responsável por remover todas as telas da pilha
                finishAffinity()
            }

        }
    }
}