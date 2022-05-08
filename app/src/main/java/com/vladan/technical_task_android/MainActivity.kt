package com.vladan.technical_task_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vladan.technical_task_android.model.NavigationScreen
import com.vladan.technical_task_android.ui.home.Home
import com.vladan.technical_task_android.ui.theme.TechnicalTaskAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTaskAndroidTheme {
                val navController = rememberNavController()
                Scaffold(backgroundColor = Color.Transparent) { innerPadding ->
                    NavHost(navController, startDestination = NavigationScreen.HomeScreen.route, Modifier.padding(innerPadding)) {
                        composable(NavigationScreen.HomeScreen.route) {
                            Home()
                        }
                    }
                }
            }
        }
    }
}