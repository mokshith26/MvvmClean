package com.mokshith.mvvmclean.presentation.theme.coinList

import com.mokshith.mvvmclean.domain.models.Coin


data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
