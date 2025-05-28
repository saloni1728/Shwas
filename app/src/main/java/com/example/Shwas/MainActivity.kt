package com.example.Shwas

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.Shwas.data.eventBus.NavigationEventBus
import com.example.Shwas.ui.screens.AppHostScreen
import com.example.Shwas.ui.sideEffects.OneShotEffect
import com.example.Shwas.ui.theme.ShwasTheme
import com.example.Shwas.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationEventBus: NavigationEventBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            OneShotEffect { navigationEventBus.subscribe(navController) }

            ShwasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppHostScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context?) {
        val prefs = newBase?.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        super.attachBaseContext(
            newBase?.let {
                ContextWrapper(
                    prefs?.getString(Constants.PREFERRED_LANGUAGE, Constants.DEFAULT_LANGUAGE)
                        .let { language ->
                            System.out.println("here ${newBase}")
                            val config = newBase.resources.configuration
                            config.setLocale(Locale(language))
                            newBase.createConfigurationContext(config)
                        }
                )
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShwasTheme {
        Greeting("Android")
    }
}