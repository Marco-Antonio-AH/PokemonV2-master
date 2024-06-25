package mx.marco.presentation.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mx.marco.presentation.composables.top_app_bars.MenuNotificationAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomScaffoldWithNav(
    title: String,
    currentRoute: String,
    navController: NavController,
    containerColor: Color = MaterialTheme.colorScheme.background,
    colorStatusBar: Color = Color.White,
    darkIcons: Boolean = true,
    colorIcons: Color = MaterialTheme.colorScheme.primary,
    paddingTop: Boolean = false,
    transparent: Boolean = false,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorStatusBar,
            darkIcons = darkIcons
        )
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

        }
    ) {
        Scaffold(
            topBar = {
                MenuNotificationAppBar(
                    title = title,
                    colorIcons = colorIcons,
                    transparent = transparent
                )
            },
            containerColor = containerColor
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        if (paddingTop) {
                            it
                        } else {
                            PaddingValues()
                        }
                    ),
                color = Color.White.copy(alpha = 1f)
            ) {
                content()
            }
        }
    }
}