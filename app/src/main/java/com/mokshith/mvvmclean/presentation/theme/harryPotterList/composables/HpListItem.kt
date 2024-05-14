package com.mokshith.mvvmclean.presentation.theme.harryPotterList.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            .padding(20.dp)) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            ) {
                AsyncImage(
                    model = hpList.image,
                    contentDescription = "${hpList.name} image",
                    modifier = Modifier.clip(RoundedCornerShape(20.dp)).fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Name: ${hpList.name!!}")
                Text(text = "Gender: ${hpList.gender!!}")
                Text(text = "isAlive: ${hpList.alive!!}")
            }
        }
    }

}