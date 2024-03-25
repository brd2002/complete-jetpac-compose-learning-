package com.example.blogapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.blogapp.ui.theme.BlogAppTheme
import kotlinx.coroutines.delay
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            App()
//            ListComposable()
//            Counter()
//            LaunchEffectComposable()
            App1()
        }
    }
}
@Composable
fun App(){
    var theme = remember {
        mutableStateOf(false)
    }
    BlogAppTheme(theme.value) {
        Column() {
            Text(text = "welcome to my app" , style = MaterialTheme.typography.headlineLarge)
            Button(onClick = { theme.value = !theme.value }) {
                Text(text = "Change theme ")
            }
        }
    }
}

@Preview(showBackground = true , showSystemUi = true , name = "testing screen")
@Composable
fun ListComposable() {
    val alllist = remember {
        mutableStateOf(emptyList<String>())
    }
    LaunchedEffect(key1 = Unit) {
        alllist.value = fetchdata()
    }

    LazyColumn {
        items(alllist.value){
            Text(text = it)
        }
    }
}

fun fetchdata() : List<String>{
    return listOf("bharat" , "home" , "kisan" , "binoy" ) 
}

@Composable
fun Counter() {
    val count = remember {
        mutableStateOf(0)
    }
    var key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d("count", "Counter: value is now ${count.value}")
    }

    Button(onClick = {count.value++}) {
        Text(text = "Increment the value of count")
    }
}

@Composable
fun LaunchEffectComposable() {
    var counter = remember {
        mutableStateOf(0)
    }
    var scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        Log.d("now", "LaunchEffectComposable: is started from this now ....")
        try {
            for(i in 1..100){
                counter.value++
                delay(1000)
            }
        }
        catch (e:Exception){
            Log.d("exception", "LaunchEffectComposable: ${e.message.toString()}")
        }
    }
    var text = "Counter is running ${counter.value}"
    if(counter.value == 10 ){
        text = "Counter stopped"
    }
    Text(text = text)
}
fun a () {
    Log.d("bharat", "a: this is a  function is running..")
}
fun b() {
    Log.d("bharat", "b: this is b function is running ...")
}

@Composable
fun App1() {
    var state = remember {
        mutableStateOf(::a)
    }
    Button(onClick = {state.value = ::b}) {
        Text(text = "Change the state")
    }
    LandingScreen {
        state.value
    }
}
@Composable
fun LandingScreen(onTimeout:()->Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(key1 = true) {
        delay(8000)
        currentOnTimeout()
    }
}


