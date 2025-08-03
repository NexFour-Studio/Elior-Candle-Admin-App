package com.example.elienorcandlev2.screens
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.elienorcandlev2.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.ui.draw.shadow

@Composable
fun LoginScreen(
    onForgotPassword: () -> Unit,
    onLogin: (String, String) -> Unit,
    onGoogleSignIn: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Set main background to white
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF4B1F0B)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .offset(y = 32.dp)
                    .background(Color(0xFF4B1F0B), CircleShape)
                    .border(6.dp, Color.White, CircleShape)
                    .shadow(16.dp, CircleShape, clip = false),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.elior_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(72.dp)
                )
            }
        }
        Text(
            "Candle Shop Admin",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold, // Make shop name bolder
            color = Color(0xFF1b140e),
            modifier = Modifier
                .padding(top = 36.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Username", color = Color(0xFF97734E)) },
            modifier = Modifier.fillMaxWidth(0.95f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF3EDE7),
                focusedContainerColor = Color(0xFFF3EDE7),
                unfocusedTextColor = Color(0xFF97734E),
                focusedTextColor = Color(0xFF97734E),
                unfocusedPlaceholderColor = Color(0xFF97734E),
                focusedPlaceholderColor = Color(0xFF97734E)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password", color = Color(0xFF97734E)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.95f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF3EDE7),
                focusedContainerColor = Color(0xFFF3EDE7),
                unfocusedTextColor = Color(0xFF97734E),
                focusedTextColor = Color(0xFF97734E),
                unfocusedPlaceholderColor = Color(0xFF97734E),
                focusedPlaceholderColor = Color(0xFF97734E)
            )
        )
        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                onLogin(username, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7B3F00),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
        ) {
            Text("Login", fontWeight = FontWeight.Bold) // Make login button text bold
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onGoogleSignIn() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF4B1F0B)
            ),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo), // You need to add this drawable
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with Google", fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = onForgotPassword) {
            Text("Forgot Password?", textDecoration = TextDecoration.Underline)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
