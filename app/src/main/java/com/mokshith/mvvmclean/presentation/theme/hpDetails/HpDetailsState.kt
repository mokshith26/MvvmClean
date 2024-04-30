package com.mokshith.mvvmclean.presentation.theme.hpDetails

import com.mokshith.mvvmclean.domain.models.HPModel
import com.mokshith.mvvmclean.domain.models.HPModelDetails

data class HpDetailsState(
    val isLoading: Boolean = false,
    val hpDetails: List<HPModelDetails> = emptyList(),
    val error: String = ""
)
