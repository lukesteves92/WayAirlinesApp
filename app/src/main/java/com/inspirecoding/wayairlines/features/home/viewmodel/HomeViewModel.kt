package com.inspirecoding.wayairlines.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirecoding.wayairlines.data.util.singleorthrow.singleOrThrow
import com.inspirecoding.wayairlines.domain.usecase.GetFlightsUseCase
import com.inspirecoding.wayairlines.extensions.string.groupFlightsByCategory
import com.inspirecoding.wayairlines.features.home.action.HomeAction
import com.inspirecoding.wayairlines.features.home.state.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFlightsUseCase: GetFlightsUseCase
): ViewModel() {

    private val pendingActions = MutableSharedFlow<HomeAction>()

    private var _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.ShowLoading)
    var state: StateFlow<HomeState> = _state

    init {
        handleActions()
        getFlights(firstCall = true)
    }

    private fun handleActions() {
        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                   is HomeAction.GetFlights -> getFlights(firstCall = false)
                }
            }
        }
    }

    fun submitAction(action: HomeAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun getFlights(firstCall: Boolean) {
        if (!firstCall) {
            HomeState.ShowLoading.updateState()
        }
        viewModelScope.launch {
            getFlightsUseCase.getFlights().singleOrThrow(
                success = { data ->
                    HomeState.ShowFlightsInfo(
                        flights = data?.groupFlightsByCategory().orEmpty()
                    ).updateState()
                },
                error = {
                    HomeState.UpdateErrorView.updateState()
                }
            )
        }
    }

    private fun HomeState.updateState() = _state.update { this }
}