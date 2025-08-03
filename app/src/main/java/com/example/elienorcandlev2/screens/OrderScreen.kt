package com.example.elienorcandlev2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen() {
    // This composable is no longer needed for navigation or top/bottom bars
    // Use OrdersScreenContent in MainScreen instead
    OrdersScreenContent(padding = PaddingValues(0.dp))
}

@Composable
fun OrdersScreenContent(padding: PaddingValues) {
    val orders = listOf(
        Triple("Eleanor Hayes", "Pending", "#12345"),
        Triple("Ethan Bennett", "Urgent", "#12346"),
        Triple("Chloe Carter", "Processing", "#12347"),
        Triple("Noah Thompson", "Pending", "#12348"),
        Triple("Isabella Mitchell", "Urgent", "#12349"),
        Triple("Liam Foster", "Processing", "#12350")
    )
    TabRow()
    orders.forEach { (name, status, id) ->
        OrderItem(name, status, id)
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

@Composable
fun TabRow() {
    val tabs = listOf("Order Queue", "Rush Orders", "Needs Crafting", "In Workshop", "En Route", "Delivered")
    var selectedTab by remember { mutableIntStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = Color(0xFFF97B1A)
            )
        },
        containerColor = Color(0xFFFCFAF8)
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                selectedContentColor = Color(0xFF1C130D),
                unselectedContentColor = Color(0xFF9E6D47)
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(vertical = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
