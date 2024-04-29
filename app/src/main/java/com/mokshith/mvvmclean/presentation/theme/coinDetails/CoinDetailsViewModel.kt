package com.mokshith.mvvmclean.presentation.theme.coinDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.common.ViewUtils
import com.mokshith.mvvmclean.domain.useCase.getCoinDetails.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(ViewUtils.COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String) {
        getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
                is ApiState.Success -> {
                    _state.value = CoinDetailsState(coinDetails = result.data)
                }

                is ApiState.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }

                is ApiState.Error -> {
                    _state.value =
                        CoinDetailsState(error = result.message ?: "An Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}