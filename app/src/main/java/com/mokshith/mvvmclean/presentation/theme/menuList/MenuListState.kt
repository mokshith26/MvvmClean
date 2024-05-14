package com.mokshith.mvvmclean.presentation.theme.menuList

import com.mokshith.mvvmclean.domain.models.MenuList

data class MenuListState(
    val isLoading: Boolean = false,
    val menuList: MenuList? = null,
    val error: String = ""

)
