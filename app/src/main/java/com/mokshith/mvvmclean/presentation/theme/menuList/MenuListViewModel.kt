package com.mokshith.mvvmclean.presentation.theme.menuList

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.domain.useCase.menuListUseCase.GetMenuListUseCase
import com.mokshith.mvvmclean.presentation.theme.coinDetails.CoinDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val menuListUseCase: GetMenuListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MenuListState())
    val state: State<MenuListState> = _state

    init {
        getMenu()
    }

    private fun getMenu() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                menuListUseCase.invoke().collect { menuListState ->
                    withContext(Dispatchers.Main) { // Switch back to main thread
                        when (menuListState) {
                            is ApiState.Success -> {
                                _state.value = MenuListState(menuList = menuListState.data)
                            }

                            is ApiState.Loading -> {
                                _state.value = MenuListState(isLoading = true)
                            }

                            is ApiState.Error -> {
                                _state.value =
                                    MenuListState(
                                        error = menuListState.message
                                            ?: "An Unexpected error occurred"
                                    )
                            }
                        }
                    }
                }
            }
        }


//        menuListUseCase.invoke().onEach { result ->
//            when (result) {
//                is ApiState.Success -> {
//                    _state.value = MenuListState(menuList = result.data)
//                }
//
//                is ApiState.Loading -> {
//                    _state.value = MenuListState(isLoading = true)
//                }
//
//                is ApiState.Error -> {
//                    _state.value =
//                        MenuListState(error = result.message ?: "An Unexpected error occurred")
//                }
//            }
//        }.launchIn(viewModelScope)
    }
}