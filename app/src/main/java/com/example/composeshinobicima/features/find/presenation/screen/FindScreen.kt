package com.example.composeshinobicima.features.find.presenation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.composeshinobicima.features.home.presentaion.compoents.HomeMoviesList
import com.example.composeshinobicima.features.home.presentaion.viewmodel.HomeAction

@Composable
fun FindScreen(navController: NavController) {

    Column {
        val findViewModel = hiltViewModel<FindViewModel>()
        val state by findViewModel.viewStates.collectAsState()

        OutlinedTextField(
            value = state.query.data?:"",
            onValueChange = {  }, // now valid
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


        MediaTypeList(state.mediaType.data?:MediaType.All) {
            findViewModel.executeAction(FindAction.ChangeMediaType(it))
        }


        when(state.mediaType.data){
            MediaType.All->{

                findViewModel.executeAction(FindAction.GetTrendingAll)

                FindMediaGridList(state.media)


            }
            MediaType.Movies->{
                findViewModel.executeAction(FindAction.GetTrendingMovies)

                FindMediaGridList(state.media)
            }
            MediaType.Tv->{
                findViewModel.executeAction(FindAction.GetTrendingTv)

                FindMediaGridList(state.media)

            }
            MediaType.People ->{
                findViewModel.executeAction(FindAction.GetTrendingPeople)

                FindMediaGridList(state.media)

            }
            else -> {}
        }


    }


}
