package com.mokshith.mvvmclean.data.remote.dto.menu

import com.mokshith.mvvmclean.domain.models.MenuList

data class MenuListDto(
    val categories: List<Category>
)

fun MenuListDto.getMenuList(): MenuList{
    return MenuList(
        categories = categories
    )
}