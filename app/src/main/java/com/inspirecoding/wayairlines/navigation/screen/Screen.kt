package com.inspirecoding.wayairlines.navigation.screen

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
}