package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FoodMenuScreen(
                        onOrderClick = { foodName, foodPrice ->
                            val intent = Intent(this, OrderActivity::class.java)
                            intent.putExtra("food_name", foodName)
                            intent.putExtra("food_price", foodPrice)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FoodMenuScreen(onOrderClick: (String, String) -> Unit) {
    var selectedFood by remember { mutableStateOf("") }
    var selectedPrice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("FOOD ORDERING APP", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text("Select Your Food", fontSize = 20.sp)
        Spacer(Modifier.height(24.dp))

        FoodItem("Burger", "₹120", selectedFood == "Burger") {
            selectedFood = "Burger"; selectedPrice = "₹120"
        }
        FoodItem("Pizza", "₹180", selectedFood == "Pizza") {
            selectedFood = "Pizza"; selectedPrice = "₹180"
        }
        FoodItem("Sandwich", "₹100", selectedFood == "Sandwich") {
            selectedFood = "Sandwich"; selectedPrice = "₹100"
        }
        FoodItem("Pasta", "₹150", selectedFood == "Pasta") {
            selectedFood = "Pasta"; selectedPrice = "₹150"
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { onOrderClick(selectedFood, selectedPrice) },
            enabled = selectedFood.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ORDER NOW")
        }
    }
}

@Composable
fun FoodItem(foodName: String, price: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text("$foodName - $price", fontSize = 18.sp)
    }
}
