package mx.marco.presentation.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.marco.presentation.composables.screens.Screen
import mx.marco.presentation.navigation.Screens
import mx.marco.presentation.screens.home.PokemonMap
import mx.marco.presentation.theme.LocalSpacing
import mx.marco_ah.R

@Composable
fun Act1Screen(
    navController: NavController,
    viewModel: FavoritiesViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val uiState by viewModel.uiState.collectAsState()

    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_1
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokedex_splashrecortadp),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .offset( x = 8.dp, y = -40.dp)

                .fillMaxHeight(0.40f)
                .fillMaxWidth(0.85f)
                .verticalScroll(rememberScrollState())
                .align( BiasAlignment(horizontalBias = 0f, verticalBias = 0.0f) ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            uiState.listPokemon.forEach { pokemon ->
                Column(
                    modifier = Modifier
                        .offset(y = -26.dp)
                        .padding(top = 24.dp)
                        .fillMaxWidth(0.8f)
                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "pokemonData",
                                PokemonMap(
                                    name = pokemon.name,
                                    number = pokemon.number,
                                    description = pokemon.description,
                                    stripe = pokemon.stripe,
                                    types = pokemon.types,
                                    typeStats = pokemon.typeStats,
                                    abilities = pokemon.abilities
                                )
                            )
                            navController.navigate(Screens.POKEMON_DESCRIPTION)
                        }
                ) {
                    Card {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = pokemon.stripe,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .aspectRatio(1f)
                                    .padding(spacing.spaceMedium),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(spacing.spaceMedium))
                            Text(
                                text = pokemon.number.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(spacing.spaceLarge))
                            Text(
                                text = pokemon.name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}
