package com.mokshith.mvvmclean.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mokshith.mvvmclean.presentation.theme.coinDetails.CoinDetailsScreen
import com.mokshith.mvvmclean.presentation.theme.coinList.CoinListScreen
import com.mokshith.mvvmclean.presentation.theme.harryPotterList.HarryPotterListScreen
import com.mokshith.mvvmclean.presentation.theme.hpDetails.HarryPotterDetailsScreen
import com.mokshith.mvvmclean.presentation.theme.menuList.MenuListScreen

@Composable
fun ScreenNavigation() {
    //implementation(libs.androidx.navigation.compose)
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MenuListScreen.route
    ) {
        composable(route = Screen.CoinListScreen.route) {
            CoinListScreen(navController)
        }
        composable(route = Screen.CoinDetailsScreen.route + "/{coinId}") {
            CoinDetailsScreen()
        }

        composable(route = Screen.HarryPotterListScreen.route) {
            HarryPotterListScreen(navController)
        }

        composable(route = Screen.HarryPotterDetailsScreen.route + "/{id}") {
            HarryPotterDetailsScreen(navController)
        }

        composable(route = Screen.MenuListScreen.route) {
            MenuListScreen()
        }
    }
}