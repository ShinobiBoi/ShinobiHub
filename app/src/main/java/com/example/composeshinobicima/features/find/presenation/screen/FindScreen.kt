package com.example.composeshinobicima.features.find.presenation.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.components.MediaTypeList
import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.find.presenation.components.FindMediaGridList
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindAction
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindScreen(navController: NavController) {

    Column {
        val findViewModel = hiltViewModel<FindViewModel>()
        val state by findViewModel.viewStates.collectAsState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )



        LaunchedEffect(Unit) {
            findViewModel.executeAction(FindAction.GetGenreList)
        }

        var filterList = emptyList<Genre>()
        state.genres.data?.let {
            filterList=it.filter { it.selected }
        }


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
                IconButton(onClick = { showBottomSheet = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = Color.Gray
                    )
                }
            }

        )


        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                dragHandle = {}
            ) {
                FilterSheetContent(
                    onApply = { /* apply filters */ showBottomSheet = false },
                    onCancel = { showBottomSheet = false },
                    toggleGenre = { findViewModel.executeAction(FindAction.ToggleGenre(it)) },
                    genres = state.genres.data?: emptyList()
                )
            }
        }



        MediaTypeList(state.mediaType.data ?: MediaType.All) {
            findViewModel.executeAction(FindAction.ChangeMediaType(it))
        }


        LaunchedEffect(
            key1 = state.mediaType.data,
            key2 = state.query.data,
            key3 = filterList
        ) {

            if (state.query.data.isNullOrEmpty()) {
                when (state.mediaType.data) {
                    MediaType.All -> {
                        findViewModel.executeAction(FindAction.GetTrendingAll(filterList))

                    }

                    MediaType.Movies -> {
                        findViewModel.executeAction(FindAction.GetTrendingMovies(filterList))

                    }

                    MediaType.Tv -> {
                        findViewModel.executeAction(FindAction.GetTrendingTv(filterList))


                    }

                    MediaType.People -> {
                        findViewModel.executeAction(FindAction.GetTrendingPeople(filterList))


                    }

                    else -> {}
                }

            } else {

                when (state.mediaType.data) {
                    MediaType.All -> {

                        findViewModel.executeAction(
                            FindAction.SearchMulti(
                                state.query.data ?: "",
                                filterList
                            )
                        )
                    }

                    MediaType.Movies -> {
                        findViewModel.executeAction(
                            FindAction.SearchMovie(
                                state.query.data ?: "",
                                filterList
                            )
                        )

                    }

                    MediaType.Tv -> {
                        findViewModel.executeAction(
                            FindAction.SearchTv(
                                state.query.data ?: "",
                                filterList
                            )
                        )


                    }

                    MediaType.People -> {
                        findViewModel.executeAction(
                            FindAction.SearchPeople(
                                state.query.data ?: "",
                                filterList
                            )
                        )


                    }

                    else -> {}
                }


            }
        }

        FindMediaGridList(state.media){id,type ->
            navController.navigate(ScreenResources.DetailScreenRoute(id,type))
        }


    }


}


@Composable
fun FilterSheetContent(
    onApply: () -> Unit,
    onCancel: () -> Unit,
    toggleGenre: (genre: Genre) -> Unit,
    genres: List<Genre>
) {
    var expanded by remember { mutableStateOf(false) }
    val highlightColor = colorResource(R.color.light_blue)

    val visibleGenres = if (expanded) genres else genres.take((genres.size / 2).coerceAtLeast(1))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(16.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Black,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onCancel() }
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Search Filters",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.size(28.dp))
        }

        FlowRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            visibleGenres.forEach { item ->


                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = if (item.selected)
                        BorderStroke(2.dp, highlightColor)
                    else
                        BorderStroke(1.dp, Color.Transparent),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
                    onClick = { toggleGenre(item) }
                ) {
                    Text(
                        text = item.name ?: "",
                        color = if (item.selected) highlightColor else Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                        fontSize = 15.sp
                    )
                }
            }
        }

        if (genres.size > visibleGenres.size) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Show More")
            }
        } else if (expanded) {
            TextButton(
                onClick = { expanded = false },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Show Less")
            }
        }

        Button(
            onClick = onApply,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Filters")
        }

        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

