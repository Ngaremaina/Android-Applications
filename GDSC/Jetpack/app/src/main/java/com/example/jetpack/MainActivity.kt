package com.example.jetpack

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.ui.theme.JetpackTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")

                    Form()

                }
            }
        }
    }
}

@Composable
fun Form(){
    //var email:MutableState
    var email by remember { mutableStateOf(" ")}
    //to move the email to viewModel to be used as liveData, you can create a mutableLiveData, as
    // [private var _email: MutableLivedata<String> = mu.tablelivedata() then get it from the mutable String in,
    //[var email:Livedata<string> = _email] then create a function to change the email as,
    //[fun changeEmail(value:String){_email.value = value] then initialize the viewModel as,
    //[viewModel() ]or the other way provided by Compose. Then observe the state.


    var loading by remember {mutableStateOf(false)}
    

    Column(modifier= Modifier.padding(10.dp)){
        Text(text = "Login", style = MaterialTheme.typography.h5)
        OutlinedTextField(value = " ", onValueChange = { email=it }, label={ Text(text = "email")})
        //email = it allows for re-composition (when the value is changed)

    }
    Button(onClick = {loading =! loading}) {
        if (loading){
            Icon(Icons.Default.PlayArrow, contentDescription = "")
        }
        else {
            Text(text = "Submit")}


    }
}
@Composable
fun Input(email: String, onChanged:(value:String)->Unit){

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackTheme {
        Greeting("Android")
    }
}
@Composable
fun StoreDetails(name:String, email:String, phone:Number, age:Number){

}

//to call a Composable inside a suspending function in a coroutineScope use remember(coroutineScope)