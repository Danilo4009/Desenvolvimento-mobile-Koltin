package com.example.teste1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun telaInclusao(navController: NavHostController, db: Database) {
    val coroutineScope = rememberCoroutineScope()
    val api = remember { ViaCepService.create() }
    var cepData by remember { mutableStateOf<CepResponse?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .background(color = Color(0xFFFF9800))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Inclusão",
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        var nome by remember { mutableStateOf("") }
        var telefone by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var cep by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text(text = "Telefone") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text(text = "Digite o CEP") }
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    errorMessage = null
                    try {
                        val response = api.fetchCep(cep)
                        if (response.isSuccessful && response.body() != null) {
                            val responseBody = response.body()!!
                            if (responseBody.erro == true) {
                                errorMessage = "CEP INVÁLIDO"
                            } else {
                                cepData = responseBody
                                // Cria o objeto Usuario com os dados obtidos do cepData
                                val usuario = Usuario(
                                    nome = nome,
                                    email = email,
                                    ddd = cepData!!.ddd,
                                    telefone = telefone,
                                    cep = cepData!!.cep,
                                    logradouro = cepData!!.logradouro,
                                    bairro = cepData!!.bairro,
                                    localidade = cepData!!.localidade,
                                    uf = cepData!!.uf
                                )
                                // Adiciona o usuario no banco de dados
                                db.addUsuario(usuario)
                            }
                        } else {
                            errorMessage = "Falha na consulta do CEP"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message
                    }
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Consultar e Incluir")
        }

        Spacer(modifier = Modifier.size(16.dp))
        if (cepData != null) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("CEP: ${cepData!!.cep}")
                Text("Logradouro: ${cepData!!.logradouro}")
                Text("Bairro: ${cepData!!.bairro}")
                Text("Localidade: ${cepData!!.localidade}")
                Text("UF: ${cepData!!.uf}")
                Text("DDD: ${cepData!!.ddd}")
            }
        }
        errorMessage?.let {
            Text("Erro: $it", color = Color.Red)
        }
        Row {
            Button(
                onClick = { navController.navigate("tela1") },
                modifier = Modifier.width(160.dp)
            ) {
                Text(text = "Voltar")
            }
        }
    }
}
