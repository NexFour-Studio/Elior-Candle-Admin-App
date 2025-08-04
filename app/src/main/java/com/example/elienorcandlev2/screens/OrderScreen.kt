package com.example.elienorcandlev2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen() {
    // This composable is no longer needed for navigation or top/bottom bars
    // Use OrdersScreenContent in MainScreen instead
    OrdersScreenContent()
}

@Composable
fun OrdersScreenContent() {
    val orders = listOf(
        Triple("Eleanor Hayes", "Order Queue", "#12345"),
        Triple("Ethan Bennett", "Rush Orders", "#12346"),
        Triple("Chloe Carter", "Needs Crafting", "#12347"),
        Triple("Noah Thompson", "In Workshop", "#12348"),
        Triple("Isabella Mitchell", "En Route", "#12349"),
        Triple("Liam Foster", "Delivered", "#12350")
    )
    val filters = listOf("Order Queue", "Rush Orders", "Needs Crafting", "In Workshop", "En Route", "Delivered")
    var selectedFilter by remember { mutableIntStateOf(0) }
    FilterChipsRow(filters, selectedFilter) { selectedFilter = it }
    val filteredOrders = orders.filter { it.second == filters[selectedFilter] }
    filteredOrders.forEach { (name, status, id) ->
        OrderItem(name, status, id)
    }
}

@Composable
fun FilterChipsRow(filters: List<String>, selectedIndex: Int, onSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEachIndexed { index, label ->
            FilterChip(
                selected = selectedIndex == index,
                onClick = { onSelected(index) },
                label = { Text(label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFFF97B1A),
                    selectedLabelColor = Color.White,
                    containerColor = Color(0xFFF4ECE6),
                    labelColor = Color(0xFF1C130D)
                )
            )
        }
    }
}

@Composable
fun OrderItem(name: String, status: String, orderId: String) {
    val buttonLabel = when (status) {
        "Pending" -> "Start Crafting"
        "Urgent" -> "Ship"
        "Processing" -> "Send Tracking"
        else -> "Update"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Customer: $name", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color(0xFF1C130D))
            Text(text = "Status: $status", fontSize = 14.sp, color = Color(0xFF9E6D47))
            Text(text = "Order ID: $orderId", fontSize = 14.sp, color = Color(0xFF9E6D47))
        }
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4ECE6)),
            shape = CircleShape,
            modifier = Modifier.height(32.dp)
        ) {
            Text(buttonLabel, color = Color(0xFF1C130D), fontSize = 14.sp)
        }
    }
}
