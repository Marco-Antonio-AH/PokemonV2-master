package mx.marco.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.marco.data.local.DatabasePokemon
import mx.marco.data.remote.PokemonApiService
import mx.marco.data.repository.LocalPokemonRepositoryImp
import mx.marco.data.repository.PokemonRepositoryImpl
import mx.marco.domain.preferences.Preferences
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.repository.PokemonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventRepository(
        api: PokemonApiService,
        preferences: Preferences

    ): PokemonRepository {
        return PokemonRepositoryImpl(api,preferences)
    }



    @Provides
    @Singleton
    fun provideLocalRepository(
        database: DatabasePokemon
    ): PokemonLocalRepository {
        return LocalPokemonRepositoryImp(
            database.favoritePokemonDao()
        )
    }



}