package com.example.k2024_01_30a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import coil.compose.rememberImagePainter

@Composable
fun DisplayImage() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberImagePainter("https://i.imgur.com/mFDP96p.png"),
            contentDescription = "Your Image",
            modifier = Modifier.fillMaxSize()
        )
    }
}

class MainActivity : ComponentActivity() {

    private val setVar = "PGB_LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "CSE 3150")
        }
        Log.d(setVar, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(setVar, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(setVar, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(setVar, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(setVar, "onStop")
    }

}