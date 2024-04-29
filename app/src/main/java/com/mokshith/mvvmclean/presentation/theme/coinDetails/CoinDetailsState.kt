package com.mokshith.mvvmclean.presentation.theme.coinDetails

import com.mokshith.mvvmclean.domain.models.CoinDetails


data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetails? = null,
    val error: String = ""


    // val coins: CoinDetails = null,
)
