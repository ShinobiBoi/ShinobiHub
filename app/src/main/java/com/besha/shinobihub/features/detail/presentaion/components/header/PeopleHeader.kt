package com.besha.shinobihub.features.detail.presentaion.components.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.besha.shinobihub.R
import com.besha.shinobihub.features.detail.domain.model.DetailMediaItem


@Composable
fun PeopleHeader(posterHeight: Dp, mediaItem: DetailMediaItem?, sessionId: String?) {


    Row(modifier = Modifier.padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically) {
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

        Column(modifier = Modifier) {

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = mediaItem?.resolvedTilte ?: "",
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black)
            )

            Text(
                modifier = Modifier,
                //.padding(top = 8.dp),
                text = "Born at: ${mediaItem?.birthday}",
                color = colorResource(R.color.gray)
            )

            Text(

                text = "Known for: ${mediaItem?.known_for_department}",
                color = colorResource(R.color.gray)
            )

            if (sessionId != null) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_save),
                                contentDescription = "save button",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Save",
                                color = colorResource(R.color.gray),
                                modifier = Modifier.padding(start = 6.dp),
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }



        }


    }

}
