package com.example.composeshinobicima.features.home.presentaion.compoents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshinobicima.appcore.components.PosterList
import com.example.composeshinobicima.appcore.mvi.MediaViewState

@Composable
fun HomeMoviesList(state: MediaViewState, title: String) {

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = title,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 13.dp, top = 50.dp),
            textAlign = TextAlign.Start
        )


        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.data.isNullOrEmpty() -> {
                Text(
                    text = "It's empty here!",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            else -> {
                PosterList(state.data)
            }
        }
    }

}