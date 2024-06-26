package mx.marco.domain.repository


import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonResponseLimitList
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.util.Resource


interface PokemonRepository {

    suspend fun getPokemon(pokemonId: String): Resource<PokemonResponse>

    suspend fun getSpecies(pokemonId: String): Resource<PokemonSpeciesResponse>

    suspend fun getList(): Resource<PokemonListResponse>

    suspend fun getLimitList( offset: Int, limit : Int): Resource<PokemonResponseLimitList>

}