package mx.marco.presentation.screens.home

sealed interface HomeViewEvent{
    data class UpdateSearch(val newText: String): HomeViewEvent





}