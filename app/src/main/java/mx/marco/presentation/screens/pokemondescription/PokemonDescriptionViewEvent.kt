package mx.marco.presentation.screens.pokemondescription

import mx.marco.presentation.screens.home.PokemonMap

sealed interface PokemonDescriptionViewEvent{
    data class  SavePokemon(val pokemon: PokemonMap): PokemonDescriptionViewEvent


}