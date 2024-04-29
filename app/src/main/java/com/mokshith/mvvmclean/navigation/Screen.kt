package com.mokshith.mvvmclean.navigation

sealed class Screen(val route: String) {

    data object CoinListScreen : Screen("coins_list_screen")

    data object CoinDetailsScreen : Screen("coins_details_screen")
}