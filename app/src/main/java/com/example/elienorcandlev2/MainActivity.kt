package com.example.elienorcandlev2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.example.elienorcandlev2.screens.LoginScreen
import com.example.elienorcandlev2.screens.ForgotPasswordScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private var screen by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        screen = if (auth.currentUser != null) "dashboard" else "login"
        setContent {
            com.example.elienorcandlev2.ui.theme.ElienorCandleV2Theme {
                when (screen) {
                    "login" -> {
                        LoginScreen(
                            onForgotPassword = { screen = "forgot-password" },
                            onLogin = { email, password ->
                                loginUser(email, password) { success ->
                                    if (success) screen = "dashboard"
                                }
                            },
                            onGoogleSignIn = { startGoogleSignIn() }
                        )
                    }
                    "forgot-password" -> {
                        ForgotPasswordScreen(
                            onBackToLogin = { screen = "login" }
                        )
                    }
                    "dashboard" -> {
                        com.example.elienorcandlev2.screens.MainScreen()
                    }
                }
            }
        }
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken ?: "") { success ->
                        if (success) {
                            screen = "dashboard"
                        }
                    }
                } catch (e: ApiException) {
                    Log.w("FIREBASE", "Google sign in failed", e)
                    runOnUiThread {
                        Toast.makeText(this, "Google sign-in failed: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit = {}) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("FIREBASE", "Logged in: ${user?.email}")
                    runOnUiThread {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        onResult(true)
                    }
                } else {
                    Log.e("FIREBASE", "Login failed: ${task.exception?.message}")
                    runOnUiThread {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        onResult(false)
                    }
                }
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String, onResult: (Boolean) -> Unit = {}) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("FIREBASE", "Google sign-in success: ${user?.email}")
                    runOnUiThread {
                        Toast.makeText(this, "Google sign-in successful!", Toast.LENGTH_SHORT).show()
                        onResult(true)
                    }
                } else {
                    Log.w("FIREBASE", "Google sign-in failed", task.exception)
                    runOnUiThread {
                        Toast.makeText(this, "Google sign-in failed: ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                        onResult(false)
                    }
                }
            }
    }

    private fun startGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    fun logoutUser(onResult: () -> Unit = {}) {
        val user = auth.currentUser
        val isGoogleUser = user?.providerData?.any { it.providerId == GoogleAuthProvider.PROVIDER_ID } == true
        auth.signOut()
        if (isGoogleUser) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut().addOnCompleteListener {
                runOnUiThread {
                    Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show()
                    screen = "login"
                    onResult()
                }
            }
        } else {
            runOnUiThread {
                Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show()
                screen = "login"
                onResult()
            }
        }
    }
}
