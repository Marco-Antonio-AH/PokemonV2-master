package mx.marco.presentation.screens.pokemondescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import mx.marco_ah.R

@Composable
fun PokemonDescriptionScreen(
    navController: NavController,
    viewModel: PokemonDescriptionViewModel = hiltViewModel()
) {
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.POKEMON_DESCRIPTION
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokedex_splashrecortadp),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.matchParentSize()
            )
            val spacing = LocalSpacing.current
            val pokemonData =
                navController.previousBackStackEntry?.savedStateHandle?.get<PokemonMap>("pokemonData")

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(modifier = Modifier.padding(end = 210.dp, top = 34.dp),
                    onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Column(
                    modifier = Modifier
                        .padding(start = 66.dp, top = 40.dp)
                        .fillMaxHeight(.54f)
                        .fillMaxWidth(.85f)
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
                        text = "${pokemonData?.description}",
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                BasicButton(
                    modifier = Modifier
                        .fillMaxHeight(.2f)
                        .padding(start = 30.dp, top = 12.dp),
                    label =R.string.favoritos ,

                    onClick = {
                        pokemonData?.let {viewModel.onEvent( PokemonDescriptionViewEvent.SavePokemon(it) )}
                    },
                    containerColor = Color.Black,
                    contentColor = Color.Red
                )
                Column(
                    modifier = Modifier
                        .padding(top = 84.dp)
                        .fillMaxHeight(.6f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Id: ${pokemonData?.number}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier,
                    )
                    Text(
                        text = "Nombre: ${pokemonData?.name}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )

                    val typesString =
                        pokemonData?.types?.joinToString("\n ") // Une los tipos en una sola cadena separada por comas

                    Text(
                        text = "Tipos: \n$typesString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier // Ajusta el espaciado vertical según tus necesidades
                    )
                    val pokemonStatsString = pokemonData?.typeStats?.joinToString("\n")

                    Text(
                        text = "Estadisticas: \n$pokemonStatsString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                    val pokemonAbilitiesString = pokemonData?.abilities?.joinToString("\n")
                    Text(
                        text = "Habilidades: \n$pokemonAbilitiesString",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                    println(" ${pokemonData?.typeStats} aqui stats")
                }
            }
        }
    }
}



