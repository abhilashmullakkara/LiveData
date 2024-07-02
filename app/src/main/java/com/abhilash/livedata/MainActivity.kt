package com.abhilash.livedata

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.abhilash.livedata.ui.theme.LiveDataTheme
import com.abhilash.livedata.ui.theme.manager.MyApp
import com.google.android.gms.ads.MobileAds


class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        MobileAds.initialize(this) {
        }
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}



