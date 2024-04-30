package com.mokshith.mvvmclean.presentation.theme.harryPotterList

import com.mokshith.mvvmclean.domain.models.Coin
import com.mokshith.mvvmclean.domain.models.HPModel

data class HPListState(
    val isLoading: Boolean = false,
    val hpList: List<HPModel> = emptyList(),
    val error: String = ""
)
