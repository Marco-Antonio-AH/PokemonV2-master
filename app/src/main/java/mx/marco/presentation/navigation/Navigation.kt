package mx.marco.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.marco.presentation.screens.favorites.Act1Screen
import mx.marco.presentation.screens.home.Act2Screen
import mx.marco.presentation.screens.pokemondescription.PokemonDescriptionScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.ACT_2,
    ) {
        composable(Screens.ACT_1) {
            Act1Screen(navController)
        }
        composable(Screens.ACT_2) {
            Act2Screen(navController)
        }
        composable(Screens.POKEMON_DESCRIPTION) {
            PokemonDescriptionScreen(navController)
        }
    }
}