package mx.marco.presentation.screens.pokemondescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.marco.presentation.composables.buttons.BasicButton
import mx.marco.presentation.composables.screens.Screen
import mx.marco.presentation.navigation.Screens
import mx.marco.presentation.screens.home.PokemonMap
import mx.marco.presentation.theme.LocalSpacing
import mx.marco.presentation.screens.pokemondescription.PokemonDescriptionViewEvent
import mx.marco.presentation.screens.pokemondescription.PokemonDescriptionViewModel
import mx.marco_ah.R

@Composable
fun PokemonDescriptionScreen(
    navController: NavController,
    viewModel: PokemonDescriptionViewModel = hiltViewModel()
) {
    Screen(
        navController = navController,
        buttonBack = true,
        currentRoute = Screens.POKEMON_DESCRIPTION
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Fondo
            Image(
                painter = painterResource(id = R.drawable.pokedex_splashrecortadp),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            val spacing = LocalSpacing.current
            val pokemonData = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<PokemonMap>("pokemonData")


            val previousRoute = navController
                .previousBackStackEntry
                ?.destination
                ?.route


            val showFavoriteButton = previousRoute != Screens.ACT_1

            Column(
                modifier = Modifier.fillMaxHeight(.84f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(.14f))


                Column(
                    modifier = Modifier
                        .offset(y = 11.dp)
                        .padding(start = 12.dp, top = 8.9.dp)
                        .fillMaxHeight(.58f)
                        .fillMaxWidth(.72f)
                        .background(Color(0xFF39B54A)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = pokemonData?.stripe,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(.5f)
                            .fillMaxWidth(.5f)
                            .padding(spacing.spaceMedium),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    Text(
                        text = pokemonData?.description.orEmpty(),
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.fillMaxHeight(.14f))


                if (showFavoriteButton) {
                    BasicButton(
                        modifier = Modifier.fillMaxHeight(.2f),
                        label = R.string.favoritos,
                        onClick = {
                            pokemonData?.let {
                                viewModel.onEvent(
                                    PokemonDescriptionViewEvent.SavePokemon(it)
                                )
                            }
                        },
                        containerColor = Color.Black,
                        contentColor = Color.Red
                    )
                }else{Spacer(modifier = Modifier.fillMaxHeight(.14f))}

                Spacer(modifier = Modifier.fillMaxHeight(.54f))


                Column(
                    modifier = Modifier
                        .offset(y = 12.dp)
                        .padding(end = 48.dp)
                        .fillMaxHeight(.9f)
                        .fillMaxWidth(.4f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Id: ${pokemonData?.number}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Nombre: ${pokemonData?.name}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    val typesString = pokemonData?.types?.joinToString("\n ")
                    Text(
                        text = "Tipos:\n$typesString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    val statsString = pokemonData?.typeStats?.joinToString("\n")
                    Text(
                        text = "Estadísticas:\n$statsString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    val abilitiesString = pokemonData?.abilities?.joinToString("\n")
                    Text(
                        text = "Habilidades:\n$abilitiesString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
