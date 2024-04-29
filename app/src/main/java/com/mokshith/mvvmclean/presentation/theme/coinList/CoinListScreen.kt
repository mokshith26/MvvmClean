package com.mokshith.mvvmclean.presentation.theme.coinList

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mokshith.mvvmclean.common.composobles.ErrorScreen
import com.mokshith.mvvmclean.common.composobles.LoadingScreen
import com.mokshith.mvvmclean.navigation.Screen
import com.mokshith.mvvmclean.presentation.theme.coinList.components.CoinsListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinsListItem(coin = coin, onItemClick = {
                    Log.e("TAG", "CoinListScreen: $${coin.id}", )
                    navController.navigate(
                        Screen.CoinDetailsScreen.route + "{/${coin.id}}")
                }
                )
            }
        }
        if (state.error.isNotBlank()){
            ErrorScreen(error = state.error)
        }
        if (state.isLoading){
            LoadingScreen()
        }
    }
}