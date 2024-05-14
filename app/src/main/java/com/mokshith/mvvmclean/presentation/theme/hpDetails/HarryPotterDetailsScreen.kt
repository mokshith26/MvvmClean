package com.mokshith.mvvmclean.presentation.theme.hpDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mokshith.mvvmclean.common.composobles.ErrorScreen
import com.mokshith.mvvmclean.common.composobles.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarryPotterDetailsScreen(
    navController: NavHostController,
    viewModel: HpDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Details Screen", textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { paddingIntent ->
        HarryPotterData(paddingIntent, state)
    }
}

@Composable
fun HarryPotterData(paddingIntent: PaddingValues, state: HpDetailsState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingIntent)
    ) {
        Card(
            modifier = Modifier
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            LazyColumn {

                items(state.hpDetails) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 24.dp),
                        //verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        val painter =
                            rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = state.hpDetails[0].image)
                                    .build()
                            )
                        Image(
                            painter = painter,
                            contentDescription = "null",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)
                                .clip(CircleShape),
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Name : ${state.hpDetails[0].name}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Gender : ${state.hpDetails[0].gender}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Alive  : ${state.hpDetails[0].alive}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "House : ${state.hpDetails[0].house}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Character Name : ${state.hpDetails[0].actor}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "DOB : ${state.hpDetails[0].dateOfBirth}")
                        Spacer(modifier = Modifier.height(15.dp))
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
