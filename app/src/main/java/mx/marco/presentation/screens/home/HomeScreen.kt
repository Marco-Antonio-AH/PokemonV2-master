package mx.marco.presentation.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.marco.presentation.composables.Box.PercentPosition
import mx.marco.presentation.composables.dialogs.LoadingDialog
import mx.marco.presentation.navigation.Screens
import mx.marco.presentation.composables.screens.Screen
import mx.marco.presentation.theme.LocalSpacing
import mx.marco_ah.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Act2Screen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val uiState by viewModel.getState<Act2ViewState>().collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isVisible by remember {
        mutableStateOf(false)
    }

    LoadingDialog(isVisible = uiState.isLoading)
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_2
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokedex_splashrecortadp),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(modifier = Modifier.padding(top = 26.dp)) {
            if (isVisible) {
                TextField(
                    value = viewModel.state.search,
                    onValueChange = { viewModel.onEvent(HomeViewEvent.UpdateSearch(it)) },
                    placeholder = {
                        Text(
                            text = "Buscar pokémon",
                            color = Color.White.copy(alpha = 0.7f),   // color del hint
                            fontSize = 16.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.offset(x=-14.dp)
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor           = Color.Transparent,
                        cursorColor              = Color.White,
                        focusedIndicatorColor    = Color.Transparent,
                        unfocusedIndicatorColor  = Color.Transparent,
                        disabledIndicatorColor   = Color.Transparent,
                        disabledLeadingIconColor = Color.White.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(55.dp)
                        .padding(horizontal = 58.dp,)
                        .offset(y=6.dp)
                )
            }
        }
        PercentPosition(x = .64f, y = .86f, ){
            Column(
                modifier = Modifier
                    .fillMaxHeight(.64f)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.state.search == "") {
                    val sortedList = viewModel.state.listPokemon.sortedBy { it.number }
                    sortedList.forEach {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 30.dp)
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
                                    Text(
                                        text = it.number.toString(),
                                        color = MaterialTheme.colorScheme.primary,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                    )
                                    Spacer(modifier = Modifier.width(spacing.spaceLarge))
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
                                }
                            }
                        }
                    }
                }


                LaunchedEffect(scrollState.value) {
                    if (scrollState.isScrollInProgress && scrollState.value >= scrollState.maxValue) {
                        viewModel.loadMorePokemon(context)
                    }
                }
            }
         }

        PercentPosition(x = 0.83f, y = 0.24f) {
            Box(
                modifier = Modifier
                    .offset(x=-4.dp)
                    .fillMaxHeight(.1f)
                    .fillMaxWidth(.7f)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .clickable { isVisible = !isVisible }
            )
        }

    }
}

