package com.mokshith.mvvmclean.presentation.theme.hpDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.domain.useCase.hpDetailsUseCase.GetHpDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HpDetailsViewModel @Inject constructor(
    val useCase: GetHpDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HpDetailsState())
    val state: State<HpDetailsState> = _state

    init {
        getHpList()
    }

    private fun getHpList() {
        useCase().onEach { result ->
            when (result) {
                is ApiState.Success -> {
                    _state.value = HpDetailsState(hpDetails = result.data ?: emptyList())
                }

                is ApiState.Loading -> {
                    _state.value = HpDetailsState(isLoading = true)
                }

                is ApiState.Error -> {
                    _state.value =
                        HpDetailsState(error = result.message ?: "An Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}