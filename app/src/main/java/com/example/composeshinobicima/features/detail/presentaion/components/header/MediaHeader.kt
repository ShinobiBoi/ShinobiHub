package com.example.composeshinobicima.features.detail.presentaion.components.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem


@Composable
fun MediaHeader(posterHeight: Dp, mediaItem: DetailMediaItem?, mediaType: MediaType) {


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

}
