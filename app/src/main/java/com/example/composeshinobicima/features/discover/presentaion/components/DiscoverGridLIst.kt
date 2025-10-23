package com.example.composeshinobicima.features.discover.presentaion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshinobicima.appcore.components.SmallPosterItem
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.features.find.presenation.components.GridPosterList


@Composable
fun DiscoverGridLIst(
    state: MediaViewState,
    modifier: Modifier = Modifier,
    onItemClick: (Int, MediaType) -> Unit,
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.data) { poster ->

                        MediumPosterItem(
                            poster.resolvedTitle,
                            poster.resolvedPoster,
                            poster.media_type,
                            poster.id,
                            onItemClick
                        )

                    }
                }
            }
        }
    }
}