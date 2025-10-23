package com.example.composeshinobicima.features.detail.presentaion.components.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem


@Composable
fun PeopleHeader(posterHeight: Dp, mediaItem: DetailMediaItem?) {


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
                model = "https://image.tmdb.org/t/p/original${mediaItem?.resolvedPoster}",
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
                text = "Born at: ${mediaItem?.birthday}",
                color = Color.Gray
            )

            Text(

                text = "Known for: ${mediaItem?.known_for_department}",
                color = Color.Gray
            )

        }


    }

}
