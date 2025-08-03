package com.example.elienorcandlev2.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

@Composable
fun ForgotPasswordScreen(onBackToLogin: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFAF8))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF1C130D),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onBackToLogin() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Forgot Password",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF1C130D),
                    modifier = Modifier
                        .padding(end = 48.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "Enter your email",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF1C130D),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "We'll send you a link to reset your password.",
                fontSize = 16.sp,
                color = Color(0xFF1C130D),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Email input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text("Email", color = Color(0xFF9E6D47))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF4ECE6),
                    unfocusedContainerColor = Color(0xFFF4ECE6),
                    focusedTextColor = Color(0xFF1C130D),
                    unfocusedTextColor = Color(0xFF1C130D),
                    focusedPlaceholderColor = Color(0xFF9E6D47),
                    unfocusedPlaceholderColor = Color(0xFF9E6D47),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        // Submit Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Button(
                onClick = {
                    if (email.isNotBlank()) {
                        Toast.makeText(context, "Reset link sent to: $email", Toast.LENGTH_SHORT).show()
                        onBackToLogin()
                    } else {
                        Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3F00)),
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "Request Reset",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
