package com.mokshith.mvvmclean.presentation.theme.harryPotterList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.domain.useCase.hpListUseCase.GetHPListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HarryPotterListViewModel @Inject constructor(
    private var useCase: GetHPListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HPListState())
    val state: State<HPListState> = _state

    init {
        getHpList()
    }

    private fun getHpList() {
        useCase().onEach { result ->
            when (result) {
                is ApiState.Success -> {
                    _state.value = HPListState(hpList = result.data ?: emptyList())
                }

                is ApiState.Loading -> {
                    _state.value = HPListState(isLoading = true)
                }

                is ApiState.Error -> {
                    _state.value =
                        HPListState(error = result.message ?: "An Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}