package com.mokshith.mvvmclean.presentation.theme.menuList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mokshith.mvvmclean.common.composobles.ErrorScreen
import com.mokshith.mvvmclean.common.composobles.LoadingScreen
import com.mokshith.mvvmclean.presentation.theme.menuList.composables.MenuListItem

@Composable
fun MenuListScreen(
    viewModel: MenuListViewModel = hiltViewModel()
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val extraPadding by animateDpAsState(
        if (expanded) 40.dp else 0.dp, label = ""
    )

    val state = viewModel.state.value
    Box(modifier = Modifier.padding(20.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            state.menuList?.let {
                items(it.categories) { menuList ->
                    AnimatedVisibility(visible = true, enter = slideInHorizontally(animationSpec = tween(durationMillis = 5000))) {
                        MenuListItem(menuList, onItemClick = {
                            //on item click
                            expanded = !expanded

                        })
                    }

                }
            }
        }
        if (state.error.isNotBlank()) {
            ErrorScreen(error = state.error)
        }
        if (state.isLoading) {
            LoadingScreen()
        }
    }
}
