package com.example.elienorcandlev2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onLogout: () -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFAF8))
            .verticalScroll(scrollState)
    ) {
        TopAppBar(
            title = {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        "Settings",
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                    // Add invisible box to the left to balance the back arrow
                    Box(modifier = Modifier.align(Alignment.CenterStart).size(48.dp).alpha(0f))
                    // Add invisible box to the right to balance the actions slot (if any)
                    Box(modifier = Modifier.align(Alignment.CenterEnd).size(48.dp).alpha(0f))
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFFCFAF8))
        )

        Section(title = "User Profile") {
            SettingItem(label = "Manage Account")
        }

        Section(title = "General Settings") {
            SettingItem(label = "Site Name", subLabel = "Scented Flames")
            SettingItem(label = "Contact Email", subLabel = "support@scentedflames.com")
        }

        Section(title = "Payment Gateway Settings") {
            SettingItem(label = "Payment Gateways", subLabel = "Stripe, PayPal")
        }

        Section(title = "Storefront Customization") {
            SettingItem(label = "Appearance", subLabel = "Theme: Minimalist")
        }

        Section(title = "Product Categories Management") {
            SettingItem(label = "Manage Categories")
        }

        Section(title = "Discount Code Management") {
            SettingItem(label = "Manage Discounts")
        }

        Section(title = "CRM Settings") {
            SettingItem(label = "CRM Integration", subLabel = "Integrated")
        }

        Section(title = "Reporting and Analytics Preferences") {
            SettingItem(label = "Customize Reports")
        }

        Section(title = "Shipping Settings") {
            SettingItem(label = "Free Shipping Threshold", subLabel = "$50")
            SettingItem(label = "Shipping Rate", subLabel = "$5")
        }

        Section(title = "Tax Settings") {
            SettingItem(label = "Tax Rate", subLabel = "10%")
        }

        Section(title = "Notification Settings") {
            ToggleSettingItem(label = "Push Notifications")
            ToggleSettingItem(label = "Sound")
        }

        Section(title = "Basic Settings") {
            SettingItem(label = "App Version", subLabel = "v1.2.3")
            SettingItem(label = "Privacy Policy")
            SettingItem(label = "Terms of Service")
        }

        Row(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4ECE6)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Logout", color = Color(0xFF1C130D), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun Section(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        Text(
            text = title,
            color = Color(0xFF1C130D),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun SettingItem(label: String, subLabel: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, color = Color(0xFF1C130D), fontWeight = FontWeight.Medium)
            subLabel?.let {
                Text(text = it, color = Color(0xFF9E6D47), fontSize = 12.sp)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFF1C130D)
        )
    }
}

@Composable
fun ToggleSettingItem(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color(0xFF1C130D))
        Switch(checked = true, onCheckedChange = { /* Handle toggle */ })
    }
}
