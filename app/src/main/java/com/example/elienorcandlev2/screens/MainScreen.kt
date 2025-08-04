package com.example.elienorcandlev2.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.elienorcandlev2.components.BottomNavigationBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import com.example.elienorcandlev2.MainActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var showSettings by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Box(Modifier.fillMaxWidth()) {
                            Text(
                                if (showSettings) "Settings" else when (selectedIndex) {
                                    0 -> "Dashboard"
                                    1 -> "Orders"
                                    else -> "App"
                                },
                                modifier = Modifier.align(Alignment.Center),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    },
                    navigationIcon = {
                        if (showSettings) {
                            IconButton(onClick = { showSettings = false }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    actions = {
                        if (!showSettings && (selectedIndex == 0 || selectedIndex == 1)) {
                            IconButton(onClick = { showSettings = true }) {
                                Icon(Icons.Default.Settings, contentDescription = "Settings")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF4B1F0B),
                        titleContentColor = Color(0xFFF3EDE7), // Light beige for contrast
                        actionIconContentColor = Color(0xFFF3EDE7),
                        navigationIconContentColor = Color(0xFFF3EDE7)
                    )
                )
                HorizontalDivider(
                    color = Color(0xFFF3EDE7).copy(alpha = 0.6f),
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        },
        bottomBar = {
            if (!showSettings) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        color = Color(0xFFF3EDE7).copy(alpha = 0.6f),
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    BottomNavigationBar(selectedIndex = selectedIndex, onItemSelected = { selectedIndex = it })
                }
            }
        },
        containerColor = Color(0xFFFCFAF8)
    ) { padding ->
        val context = LocalContext.current
        var logoutRequested by remember { mutableStateOf(false) }
        if (logoutRequested) {
            LaunchedEffect(true) {
                (context as? MainActivity)?.logoutUser {}
                logoutRequested = false
            }
        }
        if (showSettings) {
            SettingsScreen(
                onLogout = { logoutRequested = true },
                onBack = { showSettings = false }
            )
        } else {
            when (selectedIndex) {
                0 -> Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                    DashboardScreen()
                }
                1 -> Column(modifier = Modifier.padding(padding)) {
                    OrdersScreenContent()
                }
                else -> Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Screen $selectedIndex", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
        }
    }
}
