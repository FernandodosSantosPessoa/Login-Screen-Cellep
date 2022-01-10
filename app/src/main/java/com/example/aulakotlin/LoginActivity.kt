package com.example.aulakotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aulakotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
// técnica utilizada para inicializar uma variável sem que ela seja nula.
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLoginEntrar.setOnClickListener {
            val email= binding.edtLoginEmail.text.toString().trim().toLowerCase()
            val senha= binding.edtLoginSenha.text.toString().trim()

            if(email.isEmpty()){
                binding.edtLoginEmail.error="Campo Obrigatório"
                binding.edtLoginEmail.requestFocus()
            }else if(senha.isEmpty()){
                binding.edtLoginSenha.error="Campo Obrigatório"
                binding.edtLoginSenha.requestFocus()
            }else{

                //fazer o acesso ao arquivo de preferências compartilhadas
                val sharedPrefs= getSharedPreferences(
                    "cadastro_$email", Context.MODE_PRIVATE
                )
                val emailPrefs= sharedPrefs.getString("EMAIL", "")
                val senhaPrefs= sharedPrefs.getString("SENHA", "")

                //verificando que o login e senha estão corretos
                if(email == emailPrefs && senha == senhaPrefs){
                    //se enstiverem corretos uma mensagem de sucesso aparece pro usuário
                    Toast.makeText(this,"Usuário Logado", Toast.LENGTH_LONG).show()
                    //em seguida a MainActivity vai ser aberta
                    val mIntent= Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL",email)
                    startActivity(mIntent)
                    finish()
                }else{
                    //se o email e a senha estiverem errados, uma mensagem de erro deve aparecer para o usuário
                    Toast.makeText(this,"Email ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }
        // quando o botão cadastrar for clicado faça
        binding.btnLoginCadastrar.setOnClickListener{
            val mIntent= Intent(this,CadastroActivity::class.java)
            startActivity(mIntent)
        }

    }
}