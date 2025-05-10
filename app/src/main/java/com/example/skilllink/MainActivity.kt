package com.example.skilllink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.skilllink.ui.navigation.NavGraph
import com.example.skilllink.ui.theme.SkillLinkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillLinkTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ){
                    NavGraph()
                }
            }
        }
    }
}