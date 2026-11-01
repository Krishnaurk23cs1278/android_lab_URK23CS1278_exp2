package com.example.foodorderingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class OrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val foodName = intent.getStringExtra("food_name") ?: "Unknown Food"
        val foodPrice = intent.getStringExtra("food_price") ?: "₹0"

        setContent {
            MaterialTheme {
                OrderConfirmationScreen(
                    foodName = foodName,
                    foodPrice = foodPrice,
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@Composable
fun OrderConfirmationScreen(foodName: String, foodPrice: String, onBackClick: () -> Unit) {
    var showIcon by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showDetails by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200); showIcon = true
        delay(500); showTitle = true
        delay(500); showDetails = true
        delay(700); showButton = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = showIcon,
            enter = scaleIn(animationSpec = tween(600, easing = FastOutSlowInEasing)) + fadeIn(tween(600))
        ) {
            AnimatedSuccessIcon()
        }

        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(
            visible = showTitle,
            enter = fadeIn(tween(600)) + slideInVertically(tween(600))
        ) {
            Text("ORDER CONFIRMED!", fontSize = 28.sp)
        }

        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(
            visible = showDetails,
            enter = fadeIn(tween(700)) + slideInVertically(tween(700))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Food: $foodName", fontSize = 20.sp)
                Spacer(Modifier.height(10.dp))
                Text("Price: $foodPrice", fontSize = 20.sp)
                Spacer(Modifier.height(15.dp))
                Text("Thank you for your order!", fontSize = 18.sp)
            }
        }

        Spacer(Modifier.height(30.dp))

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(tween(700)) + scaleIn(tween(700))
        ) {
            Button(onClick = onBackClick) {
                Text("BACK TO MENU")
            }
        }
    }
}

@Composable
fun AnimatedSuccessIcon() {
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = tween(700, easing = FastOutSlowInEasing),
        label = "IconScale"
    )

    LaunchedEffect(Unit) { startAnimation = true }

    Text(
        text = "🎉",
        fontSize = 70.sp,
        modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale }
    )
}
