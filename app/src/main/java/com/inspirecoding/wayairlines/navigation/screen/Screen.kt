package com.inspirecoding.wayairlines.navigation.screen

import com.google.gson.Gson

sealed class Screen(val route: String) {
    private val gson = Gson()

    data object Home : Screen(route = "home")
    data object Details : Screen(route = "details")
}