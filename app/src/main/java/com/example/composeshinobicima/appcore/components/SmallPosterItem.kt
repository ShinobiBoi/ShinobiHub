package com.example.composeshinobicima.appcore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.model.MediaType


@Composable
fun SmallPosterItem(mediaItem: MediaItem,onItemClick: (Int,MediaType) -> Unit) {

    Column (modifier = Modifier.clickable(
        onClick = { onItemClick(mediaItem.id,mediaItem.media_type) }
    )){
        if (mediaItem.media_type == MediaType.People ) {
            Poster(mediaItem.name, mediaItem.profile_path)
        } else if (mediaItem.media_type == MediaType.Movies ) {
            Poster(mediaItem.title, mediaItem.poster_path)
        } else if (mediaItem.media_type == MediaType.Tv ) {
            Poster(mediaItem.name, mediaItem.poster_path)
        }

    }


}


@Composable
 fun Poster(title: String?, posterPath: String?) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .background(Color.Transparent)
                .size(width = 115.dp, height = 156.dp)
                .clip(RoundedCornerShape(10.dp))
            ,
            model = "https://image.tmdb.org/t/p/w500${posterPath ?: ""}",
            contentDescription = "",
            contentScale = ContentScale.Crop,


            )
    }
    Text(
        modifier = Modifier.width(115.dp)
            .background(Color.Transparent)
            .padding(top = 10.dp)
            .basicMarquee(),
        text = title ?: "",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        color = Color.Gray
    )

}


@Preview(showBackground = true)
@Composable
fun SmallPosterItemPreview() {
    val sampleMediaItem = MediaItem(
        id = 1,
        media_type = MediaType.Movies,
        title = "Interstellar",
        name = null,
        poster_path = "/yzqHt4m1SeY9FbPrfZ0C2Hi9x1s.jpg",
        profile_path = null,
        overview = "A team of explorers travel through a wormhole in space...",
        vote_average = 8.6,
        release_date = "2014-11-05"
    )

   // SmallPosterItem(mediaItem = sampleMediaItem)
}


