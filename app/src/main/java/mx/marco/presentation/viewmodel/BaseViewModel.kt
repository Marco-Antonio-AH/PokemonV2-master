package mx.marco.presentation.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.marco.presentation.viewstate.ViewState

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var _viewState: MutableStateFlow<ViewState>
    protected lateinit var viewState: StateFlow<ViewState>

    protected fun initViewState(state: ViewState) {
        _viewState = MutableStateFlow(state)
        viewState = _viewState.asStateFlow()
    }

    protected fun updateViewState(newViewState: ViewState) {
        _viewState.update { newViewState }
    }

    protected fun <T> currentViewState(): T = _viewState.value as T

    fun <T> getState() = viewState as StateFlow<T>

    protected fun showToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}