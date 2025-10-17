package com.example.composeshinobicima.features.home.presentaion.compoents

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeshinobicima.appcore.domain.model.MediaItem

@Composable
fun TrendingMovieBannerPager(mediaItems: List<MediaItem>) {
    val pagerState = rememberPagerState(pageCount = { mediaItems.size })
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Auto-slide logic
    LaunchedEffect(pagerState) {
        while (true) {
            kotlinx.coroutines.delay(10000)
            val nextPage = (pagerState.currentPage + 1) % mediaItems.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    // ---- The pager with overlay text ----
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val movie = mediaItems[page]

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.275f)
                .padding(horizontal = 13.dp, vertical = 24.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            // Movie image
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.backdrop_path}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Gradient overlay (for better text visibility)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                        )
                    )
            )

            // Text overlay (title + tag)
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFF2196F3))
                        .padding(4.dp),
                    text = "Trending Movies",
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = movie.title ?: "",
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.basicMarquee()
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    repeat(mediaItems.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(if (isSelected) 10.dp else 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelected) Color.White else Color.Gray)
                        )
                    }
                }
            }
        }
    }
}