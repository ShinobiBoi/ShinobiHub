package com.example.composeshinobicima.features.detail.presentaion.screen

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.components.Poster
import com.example.composeshinobicima.appcore.components.PosterList
import com.example.composeshinobicima.appcore.components.SmallPosterItem
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.video.VideoItem
import com.example.composeshinobicima.features.detail.domain.constants.DetailTab

import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailActions
import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailViewModel
import com.example.composeshinobicima.ui.theme.poppinsFamily
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("DefaultLocale")
@Composable
fun MediaDetailScreen(mediaId: Int, mediaType: MediaType, navController: NavController) {


    val viewModel = hiltViewModel<DetailViewModel>()
    val scrollState = rememberScrollState()



    LaunchedEffect(Unit) {
        when (mediaType) {
            MediaType.Movies -> {
                viewModel.executeAction(DetailActions.GetDetailMovie(mediaId))
                viewModel.executeAction(DetailActions.GetMovieVideo(mediaId))
                viewModel.executeAction(DetailActions.GetMovieCredits(mediaId))
                viewModel.executeAction(DetailActions.GetMovieSimilar(mediaId))
            }

            MediaType.Tv -> {
                viewModel.executeAction(DetailActions.GetDetailTv(mediaId))
                viewModel.executeAction(DetailActions.GetTvVideo(mediaId))
                viewModel.executeAction(DetailActions.GetTvCredits(mediaId))
                viewModel.executeAction(DetailActions.GetTvSimilar(mediaId))

            }

            MediaType.People -> {
                viewModel.executeAction(DetailActions.GetDetailPerson(mediaId))

            }

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
    val mediaItem = state.detailMediaItem.data


    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Backdrop image
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${mediaItem?.backdrop_path ?: ""}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(backdropHeight),
                contentScale = ContentScale.FillBounds
            )

            // Foreground content (poster + title)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // Offset makes poster overlap halfway over the backdrop
                    .padding(top = backdropHeight - (posterHeight * 0.35f))

            ) {
                Row(modifier = Modifier.padding(horizontal = 18.dp)) {
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
                            model = "https://image.tmdb.org/t/p/original${mediaItem?.resolvedPoster ?: ""}",
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.padding(top = (posterHeight * 0.35f))) {

                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = mediaItem?.resolvedTilte ?: "",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier,
                            //.padding(top = 8.dp),
                            text = (mediaItem?.resolvedDate?.split("-")?.getOrNull(0)
                                ?: "") +
                                    " ‧ " + (mediaItem?.genres?.getOrNull(0)?.name
                                ?: "")
                                    + " ‧ " + (if (mediaType == MediaType.Movies) mediaItem?.runtime.toString() + " min" else if (mediaType == MediaType.Tv) mediaItem?.number_of_seasons.toString() + " Seasons" else {
                            }),
                            color = Color.Gray
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.tmdb),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(45.dp, 16.dp)
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
                                                mediaItem?.vote_average
                                            )
                                        )
                                    }
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("/10.0")
                                    }
                                }
                            )


                        }
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "Original language ${mediaItem?.original_language ?: ""}",
                            color = Color.Gray
                        )

                    }


                }

                FlowRow(
                    modifier = Modifier.padding(top = 30.dp, start = 18.dp, end = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    mediaItem?.genres?.forEach { item ->


                        Card(
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
                        ) {
                            Text(
                                text = item.name ?: "",
                                color = Color.Black,
                                modifier = Modifier.padding(
                                    horizontal = 20.dp,
                                    vertical = 8.dp
                                ),
                                fontSize = 15.sp
                            )
                        }
                    }
                }


                Card(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.off_white)),
                    border = BorderStroke(2.dp, Color.Gray)


                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 18.dp)
                    ) {

                        val selectedTab = state.selectedTab.data

                        Row(
                            modifier = Modifier
                                .padding(top = 28.dp)
                                .fillMaxWidth(0.8f) // doesn’t take full width, but centered
                                .align(Alignment.CenterHorizontally)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.LightGray)
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            DetailTab.entries.filter { tab ->
                                // Show "Seasons" only for TV media type
                                mediaType == MediaType.Tv || tab != DetailTab.SEASONS
                            }.forEach { tab ->


                                TabItem(
                                    label = tab.label,
                                    isSelected = selectedTab == tab,
                                    onClick = {
                                        viewModel.executeAction(
                                            DetailActions.SwitchTab(
                                                tab
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }

                        }

                        when (selectedTab) {
                            DetailTab.INFO -> {

                                state.credits.data?.let {
                                    CreditsList(it) { id, mediaType ->
                                        navController.navigate(
                                            ScreenResources.DetailScreenRoute(
                                                id,
                                                mediaType
                                            )
                                        )

                                    }

                                }

                                Text(
                                    modifier = Modifier.padding(top = 32.dp),
                                    text = "About",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = mediaItem?.overview ?: "",
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily
                                )


                                state.videoList.data?.forEach {
                                    NativeYouTubePlayer(video = it)
                                }

                                state.similar.data?.let {
                                    Text(
                                        modifier = Modifier.padding(top = 32.dp),
                                        text = "Similar",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    PosterList(it) { id, mediaType ->
                                        navController.navigate(
                                            ScreenResources.DetailScreenRoute(
                                                id,
                                                mediaType
                                            )
                                        )

                                    }

                                }


                            }

                            DetailTab.REVIEWS -> {

                            }

                            DetailTab.SEASONS -> {

                            }

                            else -> {}


                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CreditsList(creditsResponse: CreditsResponse, onItemClick: (Int, MediaType) -> Unit) {


    creditsResponse.cast?.let {
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "Cast",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {


            items(creditsResponse.cast) { cast ->

                Column(modifier = Modifier.clickable(
                    onClick = { onItemClick(cast?.id!!, MediaType.People) }
                )) {
                    Poster(cast?.name, cast?.profile_path)
                }
            }
        }
    }

    if (!creditsResponse.crew.isNullOrEmpty()) {

        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "Crew",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {

            items(creditsResponse.crew) { crew ->
                Column(modifier = Modifier.clickable(
                    onClick = { onItemClick(crew?.id!!, MediaType.People) }
                )) {
                    Poster(crew?.name, crew?.profile_path)

                }
            }
        }

    }
}


@Composable
fun TabItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color.White else Color.LightGray
    val textColor = if (isSelected) Color.Black else Color.Gray

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun NativeYouTubePlayer(
    video: VideoItem,
) {

    if (video.site == "YouTube" && video.type == "Trailer" && !video.id.isNullOrEmpty()) {

        Log.d("TAG", "NativeYouTubePlayer: ${video}")

        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = video.name!!,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )

        AndroidView(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(220.dp),
            factory = { ctx ->
                YouTubePlayerView(ctx).apply {
                    // For Lifecycle-aware behavior:
                    (ctx as? ComponentActivity)?.lifecycle?.addObserver(this)

                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            // loadVideo expects (videoId, startSeconds)

                            youTubePlayer.cueVideo(video.key!!, 0f)
                        }
                    })
                }
            }
        )
    }
}


