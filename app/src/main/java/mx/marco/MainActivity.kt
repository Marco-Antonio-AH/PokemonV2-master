package mx.marco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.marco.presentation.navigation.Navigation
import mx.marco.presentation.theme.PmaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        val _networkAvailable = MutableStateFlow(true)
        val networkAvailable = _networkAvailable.asStateFlow()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PmaTheme {
                Navigation(navController = rememberNavController())
                val checkNetworkAvailable by networkAvailable
                    .collectAsStateWithLifecycle()
            }
        }
    }
}