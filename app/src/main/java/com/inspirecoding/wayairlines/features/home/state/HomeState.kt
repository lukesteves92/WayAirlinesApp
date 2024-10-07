package com.inspirecoding.wayairlines.features.home.state

sealed interface HomeState {
    data object ShowLoading : HomeState
    data object UpdateErrorView : HomeState
}