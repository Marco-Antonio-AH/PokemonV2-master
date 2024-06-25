package mx.marco.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.marco.data.local.DatabasePokemon
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideFestLoversDatabase(
        app: Application
    ): DatabasePokemon {
        return Room.databaseBuilder(
            app,
            DatabasePokemon::class.java,
            "fest_lovers_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}