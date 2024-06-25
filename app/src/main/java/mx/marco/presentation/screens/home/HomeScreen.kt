package mx.marco.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.marco.presentation.composables.dialogs.LoadingDialog
import mx.marco.presentation.navigation.Screens
import mx.marco.presentation.composables.screens.Screen
import mx.marco.presentation.composables.search.TextFieldSearch
import mx.marco.presentation.theme.LocalSpacing
import mx.marco_ah.R

@Composable
fun Act2Screen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val uiState by viewModel.getState<Act2ViewState>().collectAsState()
    LoadingDialog(isVisible = uiState.isLoading)
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_2
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokedex_splashrecortadp),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize()
        )

        Column(modifier = Modifier.padding(top = 26.dp)) {
            TextFieldSearch(
                value = viewModel.state.search,
                onValueTextChange = { newSearchValue ->
                    viewModel.onEvent(HomeViewEvent.UpdateSearch(newSearchValue))
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(8f)
                    .height(46.dp),
                hint = "Buscar pokemón"
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 160.dp, start = 50.dp)
                .fillMaxWidth(.9f)
                .fillMaxHeight(.5f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.state.search == "") {
                val sortedList = viewModel.state.listPokemon.sortedBy { it.number }
                sortedList.forEach {
                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(.8f)
                            .fillMaxHeight(.2f)
                            .clickable {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "pokemonData",
                                    PokemonMap(
                                        name = it.name,
                                        number = it.number,
                                        description = it.description,
                                        stripe = it.stripe,
                                        types = it.types,
                                        typeStats = it.typeStats,
                                        abilities = it.abilities
                                    )
                                )
                                navController.navigate(Screens.POKEMON_DESCRIPTION)
                            },
                    ) {
                        Card(
                            modifier = Modifier

                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {

                                AsyncImage(
                                    model = it.stripe,

                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth(.4f)
                                        .fillMaxHeight()

                                        .padding(spacing.spaceMedium),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = it.number.toString(),
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )

                                Spacer(modifier = Modifier.width(spacing.spaceLarge))
                                println("${it.number} nombre")
                                println("paso por aqui nombre")
                                Text(
                                    text = it.name,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )


                            }
                        }

                    }

                }

            } else {
                viewModel.state.filteredListPokemon.forEach {
                    println("paso por aqui despues forEach")
                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(.8f)
                            .fillMaxHeight(.2f)
                            .clickable {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "pokemonData",
                                    PokemonMap(
                                        name = it.name,
                                        number = it.number,
                                        description = it.description,
                                        stripe = it.stripe,
                                        types = it.types,
                                        typeStats = it.typeStats,
                                        abilities = it.abilities
                                    )
                                )
                                navController.navigate(Screens.POKEMON_DESCRIPTION)
                            },
                    ) {
                        Card(
                            modifier = Modifier
                                .background(Color.Cyan)
                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {


                                println("${it.number} numero")
                                println("paso por aqui numero")
                                Text(
                                    text = it.number.toString(),
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )

                                Spacer(modifier = Modifier.width(spacing.spaceLarge))
                                println("${it.number} nombre")
                                println("paso por aqui nombre")
                                Text(
                                    text = it.name,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )
                                Spacer(modifier = Modifier.width(spacing.spaceLarge))
                                Text(
                                    text = it.types.toString().removeSurrounding("[", "]"),
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )
                                println("${it.types} busqueda tipos")


                            }
                        }

                    }

                }

            }


        }


    }
}



