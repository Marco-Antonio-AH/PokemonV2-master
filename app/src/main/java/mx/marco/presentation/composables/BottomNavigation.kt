package mx.marco.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import mx.marco.presentation.navigation.Screens
import mx.marco.presentation.theme.Primary
import mx.marco_ah.R


data class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String? = null
)

private val items = listOf(
    NavigationItem(
        title = "Buscador",
        icon = R.drawable.home,
        route = Screens.ACT_2
    ),

    NavigationItem(
        title = "Favoritos",
        icon = R.drawable.baseline_star_24,
        route = Screens.ACT_1
    ),
)

@Composable
fun BottomNavigation(
    currentRoute: String,
    navController: NavController,
    BottomNav: Boolean
) {
    if (BottomNav) {
        NavigationBar(
            containerColor = Color.White,
            contentColor = Primary,
        ) {
            items.forEachIndexed { index, navigationItem ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(navigationItem.icon),
                            contentDescription = navigationItem.title,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(navigationItem.title, fontSize = 6.sp) },
                    selected = currentRoute == navigationItem.route,
                    onClick = {
                        navigationItem.route?.let {
                            if (currentRoute != it)
                                navController.navigate(it)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        }

    }

}