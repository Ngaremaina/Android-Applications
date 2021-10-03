package com.example.animation

import android.graphics.Color.green
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animation.ui.theme.AnimationTheme

import java.nio.file.Files.size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationTheme {


                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Art()



                }

            }

        }
    }
    @Composable

    fun Art() {

        var isTapped by remember{
            mutableStateOf(false)
        }
        /*val color by animateColorAsState(if(isTapped) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Green)*/

       /* val size by animateDpAsState(
            targetValue= if(isTapped)100.dp else 50.dp,
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioHighBouncy)
        )*/



        val transition = updateTransition(isTapped)

        /* val color by transition.animateColor(
            label = "color trans",
            transitionSpec = {
                if (targetState) tween(durationMillis = 1000, easing=LinearEasing)
                else spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy)
            }) {
                isTapped->if (isTapped) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Green

        }*/

        /*val size by transition.animateDp(label="size trans", transitionSpec = {
            if (targetState)
                tween(durationMillis = 1000, easing=LinearEasing)
            else spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy)
        }) {isTapped->if(isTapped)100.dp else 50.dp

        }*/

        val infiniteTransition= rememberInfiniteTransition()

        val size by infiniteTransition.animateFloat(
            initialValue = 50f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),

                )
        )

        val color by infiniteTransition.animateColor(
            initialValue = androidx.compose.ui.graphics.Color.Green,
            targetValue = androidx.compose.ui.graphics.Color.Red,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),

                repeatMode = RepeatMode.Reverse
            )

        )


        Column(modifier =  Modifier.padding(50.dp)){
            Box(modifier = Modifier
                .size(/*size = size*//*100.dp*/Dp(size))
                .background(color = color)
                .clickable {
                    isTapped = !isTapped
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationTheme {
        Greeting("Android")
    }
}