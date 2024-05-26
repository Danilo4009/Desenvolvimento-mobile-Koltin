package com.example.teste1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable

fun AppMenu(navController: NavHostController){
    Column (
        Modifier
            .background(color = Color(0xFFFF9800))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Cadastro de Pessoas",
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Column{

            Button(onClick = {navController.navigate("tela2")}, modifier = Modifier.width(160.dp)
            ) {
                Text(text = "Tela Consulta")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {navController.navigate("tela3")}, modifier = Modifier.width(160.dp)) {
                Text(text="Tela Atualização")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {navController.navigate("tela4")}, modifier = Modifier.width(160.dp)) {
                Text(text="Tela Exclusão")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {navController.navigate("tela5")}, modifier = Modifier.width(160.dp)) {
                Text(text="Tela Inclusão")
            }
        }
    }
}

