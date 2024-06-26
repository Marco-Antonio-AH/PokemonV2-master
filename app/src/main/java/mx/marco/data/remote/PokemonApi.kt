package mx.marco.data.remote


import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonResponseLimitList
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import retrofit2.http.GET

import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon/{name}/")
    suspend fun getPokemon(
        @Path("name") namePokemonApi: String,
    ): PokemonResponse


    @GET("pokemon/?offset=0&limit=1302")
    suspend fun getList(
    ): PokemonListResponse

    @GET("pokemon/")
    suspend fun getLimitList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponseLimitList

    @GET("pokemon-species/{name}/")
    suspend fun getSpecies(
        @Path("name") nameSpeciesApi: String
    ):PokemonSpeciesResponse


}
