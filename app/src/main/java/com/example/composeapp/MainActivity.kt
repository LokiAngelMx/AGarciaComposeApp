package com.example.composeapp

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeapp.presentation.screens.DetailScreen
import com.example.composeapp.presentation.screens.HomeScreen
import com.example.composeapp.presentation.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable(
                            "detail/{productId}",
                            arguments = listOf(navArgument("productId", {type = NavType.IntType}))
                        ) {
                            val productId = it.arguments?.getInt("productId") ?: 0
                            DetailScreen(productId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola 1", modifier = Modifier
            .background(Color.Red)
            .weight(1f))
        Text(text = "Hola 2", modifier = Modifier
            .background(Color.Cyan)
            .weight(1f))
        Text(text = "Hola 3", modifier = Modifier
            .background(Color.DarkGray)
            .weight(1f))
        Text(text = "Hola 4", modifier = Modifier
            .background(Color.Yellow)
            .weight(1f))
        Text(text = "Hola 5", modifier = Modifier
            .background(Color.Magenta)
            .weight(1f))
    }
}

@Composable
fun MyList() {
    val products = listOf("Hamburguesa", "Pizza", "Tacos", "Sushi", "Ensalada")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products){ product ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        print(product)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = "ProductIcon")
                Text(product)
            }
        }
    }
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(
                rememberScrollState()
            ),
    ) {
        Text("Ejemplo 1", modifier = Modifier
            .background(Color.Red)
            .width(100.dp))
        Text("Ejemplo 2", modifier = Modifier
            .background(Color.Blue)
            .width(100.dp))
        Text("Ejemplo 3", modifier = Modifier
            .background(Color.Yellow)
            .width(200.dp))
        Text("Ejemplo 4", modifier = Modifier
            .background(Color.Green)
            .width(100.dp))
    }
}

@Composable
fun MyBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Texto 1")
        Text("Texto más grande")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyComplexLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .weight(1f),
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Yellow)
                    .weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text("Texto 1")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Green)
                    .weight(1f),
                contentAlignment = Alignment.CenterEnd
                ) {
                Text("Texto 2")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .weight(1f),
        ) {

        }
    }
}

@Composable
fun MyState() {
//    var counter = remember { //Remember sirve para recordar el estado de una variable
//        mutableStateOf(0)
//    }
    // Funciona solo si no se destruye el Activity
//    var counter by remember {
//        mutableIntStateOf(0)
//    }
    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("El valor del contador es: ${counter}")
        Button(onClick = {
            counter ++
            Log.i("Counter", counter.toString())
        }) {
            Text("Incrementar")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeAppTheme {
        HomeScreen(rememberNavController())
    }
}