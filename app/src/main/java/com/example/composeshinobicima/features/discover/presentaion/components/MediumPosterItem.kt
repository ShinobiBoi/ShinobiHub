package com.example.composeshinobicima.features.discover.presentaion.components

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.domain.model.MediaType

@Composable
fun MediumPosterItem(title: String?, posterPath: String?, mediaType: MediaType, mediaId:Int, onItemClick: (Int, MediaType) -> Unit) {

    Column (modifier = Modifier.clickable(
        onClick = {
            if (!posterPath.isNullOrEmpty()){
                onItemClick(mediaId,mediaType)
            }
        }
    )){
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(width = 184.dp, height = 244.dp)
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
            color = colorResource(R.color.gray)
        )


    }


}
