package com.example.composeshinobicima.features.find.presenation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.components.MediaTypeList
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.features.find.presenation.components.FindMediaGridList
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindAction
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindViewModel

@Composable
fun FindScreen(navController: NavController) {

    Column {
        val findViewModel = hiltViewModel<FindViewModel>()
        val state by findViewModel.viewStates.collectAsState()

        OutlinedTextField(
            value = state.query.data ?: "",
            onValueChange = {
                findViewModel.executeAction(FindAction.ChangeQuery(it))
            }, // now valid
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Search movies...") },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            trailingIcon = {
                IconButton(onClick = { /* filter click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = Color.Gray
                    )
                }
            },

            )


        MediaTypeList(state.mediaType.data ?: MediaType.All) {
            findViewModel.executeAction(FindAction.ChangeMediaType(it))
        }

        if (state.query.data.isNullOrEmpty()) {
            when (state.mediaType.data) {
                MediaType.All -> {
                    findViewModel.executeAction(FindAction.GetTrendingAll)

                }

                MediaType.Movies -> {
                    findViewModel.executeAction(FindAction.GetTrendingMovies)

                }

                MediaType.Tv -> {
                    findViewModel.executeAction(FindAction.GetTrendingTv)


                }

                MediaType.People -> {
                    findViewModel.executeAction(FindAction.GetTrendingPeople)


                }

                else -> {}
            }

        }else{

            when (state.mediaType.data) {
                MediaType.All -> {

                    findViewModel.executeAction(FindAction.SearchMulti(state.query.data?:""))
                }

                MediaType.Movies -> {
                    findViewModel.executeAction(FindAction.SearchMovie(state.query.data?:""))

                }

                MediaType.Tv -> {
                    findViewModel.executeAction(FindAction.SearchTv(state.query.data?:""))


                }

                MediaType.People -> {
                    findViewModel.executeAction(FindAction.SearchPeople(state.query.data?:""))


                }

                else -> {}
            }



        }

        FindMediaGridList(state.media)


    }


}
