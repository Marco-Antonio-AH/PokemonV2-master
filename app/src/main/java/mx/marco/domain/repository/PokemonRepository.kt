package mx.marco.domain.repository

import mx.marco.domain.model.network.response.PokemonLimitListResponse
import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.util.Resource


interface PokemonRepository {

    suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse>

    suspend fun getSpecies(pokemonId: Int): Resource<PokemonSpeciesResponse>

    suspend fun getList(): Resource<PokemonListResponse>

    suspend fun getLimitList(): Resource<PokemonLimitListResponse>

}