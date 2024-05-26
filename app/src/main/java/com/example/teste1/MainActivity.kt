package com.example.teste1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste1.ui.theme.Teste1Theme

class MainActivity : ComponentActivity() {
    private lateinit var dbHelper: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Teste1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    dbHelper = Database(this   )
                    CRUD(dbHelper)
                    App(dbHelper)
                }
            }
        }
    }
}

@Composable
fun App(db: Database) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "tela1") {
        composable("tela1") { AppMenu(navController ) }
        composable("tela2") { telaConsulta(navController, db) }
        composable("tela3") { telaAtualizacao(navController, ) }
        composable("tela4") { telaExclusao(navController, ) }
        composable("tela5") { telaInclusao(navController, db) }

    }
}

@Preview
@Composable
fun AppPreview() {
    Teste1Theme {
    }
}
@Composable
fun CRUD(db: Database) {

}