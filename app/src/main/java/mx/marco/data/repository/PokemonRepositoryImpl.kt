package mx.marco.data.repository

import android.util.Log
import mx.marco.util.Resource
import mx.marco.domain.preferences.Preferences
import mx.marco.data.remote.PokemonApiService
import mx.marco.domain.model.network.response.PokemonLimitListResponse
import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.domain.repository.PokemonRepository


class PokemonRepositoryImpl(
    private val api: PokemonApiService,
    private val preferences: Preferences
) : PokemonRepository {
    override suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse> {
        return try {
            val response = api.getPokemon(pokemonId)

            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }
    override suspend fun getSpecies(pokemonId: Int): Resource<PokemonSpeciesResponse> {
        return try {
            val response = api.getSpecies(pokemonId)

            Resource.Success(response)
        } catch (e: Exception) {

            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }
    override suspend fun getList(): Resource<PokemonListResponse> {
        return try {
            println("Paso por aqui")
            val response = api.getList()
            Resource.Success(response)
        } catch (e: Exception) {
            println("error: $e")
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getLimitList(): Resource<PokemonLimitListResponse>{
        return try {
            val response = api.getLimitList()
            Resource.Success(response)
        }catch (e: Exception){
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }
}