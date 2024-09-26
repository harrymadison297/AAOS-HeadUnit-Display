package com.example.aaos_onelink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.aaos_onelink.database.DataStoreManager
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.UserInformationEntity
import com.example.aaos_onelink.ui.theme.AAOS_ONELINKTheme
import com.example.aaos_onelink.ui.theme.BackgroundColor_def
import com.example.aaos_onelink.ui.theme.currentX

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val serialDataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        setContent {
            AAOS_ONELINKTheme {
                Surface(
                    color = BackgroundColor_def
                ) {
                    MainLayout(serialDataViewModel)
                }
            }
        }
    }
}