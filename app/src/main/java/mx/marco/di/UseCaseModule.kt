package mx.marco.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.marco.data.use_case.LocalInsertPokemonUseCaseImpl
import mx.marco.data.use_case.LocalPokemonGetListUseCaseImpl
import mx.marco.data.use_case.LocalPokemonGetUseCaseImpl
import mx.marco.data.use_case.LocalPokemonUpdateUseCaseImpl
import mx.marco.data.use_case.PokemonLimitListUseCaseImpl
import mx.marco.data.use_case.PokemonListUseCaseImpl
import mx.marco.data.use_case.PokemonSpeciesUseCaseImpl
import mx.marco.data.use_case.PokemonUseCaseImpl
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.repository.PokemonRepository
import mx.marco.domain.use_case.LocalInsertPokemonUseCase
import mx.marco.domain.use_case.LocalPokemonGetAllUseCase
import mx.marco.domain.use_case.LocalPokemonGetUseCase
import mx.marco.domain.use_case.LocalPokemonUpdateUseCase
import mx.marco.domain.use_case.PokemonLimitListUseCase
import mx.marco.domain.use_case.PokemonListUseCase
import mx.marco.domain.use_case.PokemonSpeciesUseCase

import mx.marco.domain.use_case.PokemonUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideDeleteCardUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonUseCase {
        return PokemonUseCaseImpl(
            pokemonRepository
        )
    }
    @Provides
    @ViewModelScoped
    fun providePokemonSpeciesUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonSpeciesUseCase {
        return PokemonSpeciesUseCaseImpl(
            pokemonRepository
        )
    }
    @Provides
    @ViewModelScoped
    fun providePokemonListUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonListUseCase {
        return PokemonListUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun providePokemonLimitListUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonLimitListUseCase {
        return PokemonLimitListUseCaseImpl(
            pokemonRepository
        )
    }
    @Provides
    @ViewModelScoped
    fun provideLocalInsertPokemonUseCase(
        localRepository: PokemonLocalRepository
    ): LocalInsertPokemonUseCase {
        return LocalInsertPokemonUseCaseImpl(
            localRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLocalGetPokemonUseCase(
        localRepository: PokemonLocalRepository
    ): LocalPokemonGetUseCase {
        return LocalPokemonGetUseCaseImpl(
            localRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLocalGetAllPokemonUseCase(
        localRepository: PokemonLocalRepository
    ): LocalPokemonGetAllUseCase {
        return LocalPokemonGetListUseCaseImpl(
            localRepository
        )
    }


    @Provides
    @ViewModelScoped
    fun provideLocalUpdatePokemonUseCase(
        localRepository: PokemonLocalRepository
    ): LocalPokemonUpdateUseCase {
        return LocalPokemonUpdateUseCaseImpl(
            localRepository
        )
    }



}