package com.inspirecoding.wayairlines.features.home.action

sealed interface HomeAction {
    data object GetFlights : HomeAction
}