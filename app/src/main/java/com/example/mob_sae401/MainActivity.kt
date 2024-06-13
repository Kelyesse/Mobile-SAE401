package com.example.mob_sae401

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mob_sae401.ui.Polymedia
import com.example.mob_sae401.ui.theme.MobSAE401Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobSAE401Theme {
                Polymedia()
            }
        }
    }
}
