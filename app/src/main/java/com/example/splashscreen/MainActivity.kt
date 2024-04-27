package com.example.splashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splashscreen.ui.theme.SplashScreenTheme


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle the splash screen transition.
        setContent {
            SplashScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val timeOfDay by viewModel.text.collectAsState()
                    SplashScreen(timeOfDay.asUiText().asString())
                }
            }
        }
    }
}

@Composable
fun SplashScreen(text: String) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimated = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alphaAnimated.animateTo(
            if (startAnimation) 0f else 1f,
            animationSpec = tween(2000)
        )
        startAnimation = true
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .padding(6.dp)
                    .size(120.dp)
                    .alpha(alpha = alphaAnimated.value),
                contentDescription = null
            )
            Text(text = text, modifier = Modifier.alpha(alpha = alphaAnimated.value))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenTheme {
        SplashScreen("Android")
    }
}