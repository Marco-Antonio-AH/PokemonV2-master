package mx.marco.presentation.screens.favorites

sealed interface FavoritiesViewEvent{
    data class OnEmailChange(val newText: String): FavoritiesViewEvent
    data class UpdateResult(val newText: String): FavoritiesViewEvent
    object OnHiddenDialogError: FavoritiesViewEvent
    object OnShowDialogError: FavoritiesViewEvent
    object Login: FavoritiesViewEvent
    object GetRandomPokemon: FavoritiesViewEvent
}