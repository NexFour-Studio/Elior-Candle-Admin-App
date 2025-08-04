package com.example.elienorcandlev2.screens

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Dashboard", "Orders", "Products", "Customers", "Analytics")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.AutoMirrored.Filled.List,
        Icons.Filled.ShoppingCart,
        Icons.Filled.Person,
        Icons.Filled.Info
    )
    NavigationBar(containerColor = Color(0xFFFCFAF8)) {
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = label
                    )
                },
                label = { Text(label, fontSize = 12.sp) },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1C130D),
                    unselectedIconColor = Color(0xFF9E6D47),
                    selectedTextColor = Color(0xFF1C130D),
                    unselectedTextColor = Color(0xFF9E6D47)
                )
            )
        }
    }
}
