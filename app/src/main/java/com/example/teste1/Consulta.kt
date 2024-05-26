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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun telaConsulta(navController: NavHostController, db: Database) {
    Column(
        Modifier
            .background(color = Color(0xFFFF9800))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Consulta",
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        var nome by remember { mutableStateOf("") }
        var usuario by remember { mutableStateOf<Usuario?>(null) }

        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )
        Spacer(modifier = Modifier.size(16.dp))

        Row {
            Button(
                onClick = { usuario = db.getUsuario(nome) },
                modifier = Modifier.width(160.dp)
            ) {
                Text(text = "Consultar")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = { navController.navigate("tela1") },
                modifier = Modifier.width(160.dp)
            ) {
                Text(text = "Voltar")
            }
        }

        usuario?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nome: ${it.nome}")
                Text(text = "Email: ${it.email}")
                Text(text = "DDD: ${it.ddd}")
                Text(text = "Telefone: ${it.telefone}")
                Text(text = "CEP: ${it.cep}")
                Text(text = "Logradouro: ${it.logradouro}")
                Text(text = "Bairro: ${it.bairro}")
                Text(text = "Localidade: ${it.localidade}")
                Text(text = "UF: ${it.uf}")
            }
        }
    }
}