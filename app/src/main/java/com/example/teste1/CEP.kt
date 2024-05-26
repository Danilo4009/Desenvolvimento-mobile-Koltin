package com.example.teste1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CEPInputComponent()
{
    Row (modifier = Modifier.padding(16.dp)){
        TextField(value = ""
            , onValueChange = {}
            , label = {Text("Digite o CEP" )}
        )
        Button(
            onClick =  {}
        ){

        }


    }
}
@Composable
fun CEPDisplayComponent()
{

}