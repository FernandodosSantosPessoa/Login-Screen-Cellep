package com.example.aulakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.aulakotlin.databinding.ActivityWebBinding

class webActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //habilitando execução de javascript
        binding.wbvWeb.settings.javaScriptEnabled= true

        //carregar um endereço da web
        binding.wbvWeb.loadUrl("https://br.cellep.com/estacaohack/")

        //definindo o webview como cliente web pradrão
        binding.wbvWeb.webViewClient= WebViewClient()

    }
}