package com.mokshith.mvvmclean.presentation.theme.harryPotterList.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mokshith.mvvmclean.domain.models.HPModel

@Composable
fun HpListItem(
    hpList: HPModel, onItemClick: (HPModel) -> Unit
) {
    if (hpList.image?.isNotEmpty() == true){
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(hpList) }
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                AsyncImage(
                    model = hpList.image,
                    contentDescription = "${hpList.name} image",
                )
            }
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                Text(text = "Name: ${hpList.name!!}")
                Text(text = "Gender: ${hpList.gender!!}")
                Text(text = "isAlive: ${hpList.alive!!}")
            }
        }
    }

}