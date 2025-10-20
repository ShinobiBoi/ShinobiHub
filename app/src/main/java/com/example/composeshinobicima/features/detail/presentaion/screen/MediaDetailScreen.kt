package com.example.composeshinobicima.features.detail.presentaion.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.domain.model.MediaType

import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailActions
import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailViewModel

@SuppressLint("DefaultLocale")
@Composable
fun MediaDetailScreen(mediaId: Int, mediaType: MediaType) {


    val viewModel = hiltViewModel<DetailViewModel>()


    LaunchedEffect(Unit) {
        when (mediaType) {
            MediaType.Movies -> viewModel.executeAction(DetailActions.GetDetailMovie(mediaId))
            MediaType.Tv -> viewModel.executeAction(DetailActions.GetDetailTv(mediaId))
            MediaType.People -> viewModel.executeAction(DetailActions.GetDetailPerson(mediaId))
            else -> {}

        }

    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    // Define proportions relative to screen size
    val backdropHeightFraction = 0.275f
    val backdropHeight = screenHeight * backdropHeightFraction
    val posterHeight = 200.dp


    val state by viewModel.viewStates.collectAsState()




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Backdrop image
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${state.detailMediaItem.data?.backdrop_path ?: ""}",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(backdropHeight),
            contentScale = ContentScale.FillBounds
        )

        // Foreground content (poster + title)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                // Offset makes poster overlap halfway over the backdrop
                .offset(y = backdropHeight-(posterHeight * 0.35f))
        ) {
            Row (modifier = Modifier.padding(horizontal = 18.dp)){
                // Poster
                Card(
                    modifier = Modifier
                        .width(140.dp)
                        .height(posterHeight),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    border = BorderStroke(2.dp, Color.White)

                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original${state.detailMediaItem.data?.resolvedPoster ?: ""}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.offset(y = (posterHeight * 0.35f))
                ) {

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = state.detailMediaItem.data?.resolvedTilte ?: "",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier,
                        //.padding(top = 8.dp),
                        text = (state.detailMediaItem.data?.resolvedDate?.split("-")?.getOrNull(0)
                            ?: "") +
                                " ‧ " + (state.detailMediaItem.data?.genres?.getOrNull(0)?.name
                            ?: "")
                                + " ‧ " + (if (mediaType == MediaType.Movies) state.detailMediaItem.data?.runtime.toString() + " min" else if (mediaType == MediaType.Tv) state.detailMediaItem.data?.number_of_seasons.toString() + " Seasons" else {
                        }),
                        color = Color.Gray
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tmdb),
                            contentDescription = null,
                            modifier = Modifier.size(45.dp, 16.dp)
                                .background(colorResource(R.color.dark_blue))
                                .padding(horizontal = 3.dp)

                        )

                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text =
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append(
                                        String.format(
                                            "%.1f",
                                            state.detailMediaItem.data?.vote_average
                                        )
                                    )
                                }
                                withStyle(style = SpanStyle(color = Color.Gray)) {
                                    append("/10.0")
                                }
                            }
                        )


                    }

                }


            }

            FlowRow(
                modifier = Modifier.padding(top = 30.dp, start = 18.dp, end = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.detailMediaItem.data?.genres?.forEach { item ->


                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
                    ) {
                        Text(
                            text = item.name ?: "",
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }


            Card(
                modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.off_white)),
                border = BorderStroke(2.dp, Color.Gray)

            ) {


            }
        }

    }
}
