package mx.marco.presentation.composables.screens

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mx.marco.presentation.composables.BottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen(
    bottomNav : Boolean = true,
    navController: NavController,
    buttonBack: Boolean = true,
    @StringRes title: Int? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    colorStatusBar: Color = Color.White,
    darkIconsStatusBar: Boolean = true,
    paddingTop: Boolean = true,
    currentRoute: String,
    iconColors: Color = MaterialTheme.colorScheme.primary,
    content: @Composable BoxScope. () -> Unit,

) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorStatusBar,
            darkIcons = darkIconsStatusBar
        )
    }
    Scaffold(
        topBar = {
            CustomTopBar(
                navController = navController,
                buttonBack = buttonBack,
                title = title,
                iconColors = iconColors
            )
        },
        bottomBar = {
            BottomNavigation(
                navController = navController,
                currentRoute = currentRoute,
                BottomNav = bottomNav
            )
        },
        containerColor = containerColor
    ) {
        Box(
            modifier = Modifier
                .padding(
                    if (paddingTop) {
                        it
                    } else {
                        PaddingValues()
                    }
                )
                .fillMaxSize()
        ) {
            content()
        }
    }
}