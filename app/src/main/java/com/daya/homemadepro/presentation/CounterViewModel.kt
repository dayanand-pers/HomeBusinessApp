package com.daya.homemadepro.presentation

import android.service.credentials.Action
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class UiState(
    val isLoading: Boolean = false,
    val counter: Int = 0)

sealed interface UiEvents {
    data class showMessage(val data: String) : UiEvents
}

sealed interface Actions{
    object incrementCounter : Actions
}

class CounterViewModel : ViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState())
    val usstateFlow = uiState.asStateFlow()

    private val uiEvents = MutableSharedFlow<UiEvents>()
    val uiEventsFlow = uiEvents.asSharedFlow()


    fun onAction(actions: Actions) = viewModelScope.launch{

        when(actions){
            Actions.incrementCounter -> {
                uiState.update {
                    it.copy(isLoading = true)

                }
                delay(1000)
                uiState.update {
                    it.copy(isLoading = false, counter =it.counter+1)
                }

                uiEvents.emit(UiEvents.showMessage("Increment Counter"))
            }
        }

    }

}