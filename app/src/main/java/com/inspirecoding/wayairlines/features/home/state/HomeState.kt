package com.inspirecoding.wayairlines.features.home.state

import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain

sealed interface HomeState {
    data object ShowLoading : HomeState
    data object UpdateErrorView : HomeState
    data class ShowFlightsInfo(val flights: Map<String, List<FlightsDataDomain>>) : HomeState
}