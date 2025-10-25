package com.example.composeshinobicima.features.detail.presentaion.components.reviewslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshinobicima.R
import com.example.composeshinobicima.features.detail.data.model.review.Review
import com.example.composeshinobicima.ui.theme.poppinsFamily


@Composable
fun ReviewsList(
    reviews: List<Review>?,
    modifier: Modifier = Modifier
) {


    if (reviews.isNullOrEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No reviews yet.",
                color = colorResource(R.color.gray),
                fontSize = 14.sp,
                fontFamily = poppinsFamily
            )
        }
    } else {
        var expanded by remember { mutableStateOf(false) }
        val itemsToShow = if (expanded) reviews else reviews.take(3)

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsToShow.forEach { review ->
                ReviewCard(review)
            }

            // Only show the toggle if there are more than 3 reviews
            if (reviews.size > 3) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (expanded) "Show less" else "Show more",
                    color = colorResource(R.color.dark_blue),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { expanded = !expanded }
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}
