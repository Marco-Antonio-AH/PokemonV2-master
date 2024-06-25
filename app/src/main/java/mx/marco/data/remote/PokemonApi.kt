package mx.marco.data.remote

import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonResponse


    @GET("pokemon/?offset=0&limit=1302")
    suspend fun getList(
    ): PokemonListResponse

    @GET("pokemon-species/{id}/")
    suspend fun getSpecies(
        @Path("id") id: Int
    ):PokemonSpeciesResponse


}
