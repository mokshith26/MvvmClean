package com.mokshith.mvvmclean.presentation.theme.menuList.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mokshith.mvvmclean.data.remote.dto.menu.Category

@Composable
fun MenuListItem(menuList: Category, onItemClick: (Category) -> Unit) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(menuList)
            }
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = menuList.strCategory)

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Localized description"
            )
        }
        HorizontalDivider()
    }

}
