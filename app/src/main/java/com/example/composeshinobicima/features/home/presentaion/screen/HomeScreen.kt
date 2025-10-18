package com.example.composeshinobicima.features.home.presentaion.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeshinobicima.appcore.components.MediaTypeList
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.features.home.presentaion.compoents.HomeGenreList
import com.example.composeshinobicima.features.home.presentaion.compoents.HomeMoviesList
import com.example.composeshinobicima.features.home.presentaion.compoents.TrendingMovieBannerPager
import com.example.composeshinobicima.features.home.presentaion.viewmodel.HomeAction
import com.example.composeshinobicima.features.home.presentaion.viewmodel.HomeViewModel


@Composable
fun HomeScreen(controller: NavController) {

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = {8})



    Scaffold { innerPadding ->
        val x = innerPadding

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {


            val viewModel = hiltViewModel<HomeViewModel>()

            val state by viewModel.viewStates.collectAsState()


            LaunchedEffect(Unit) {
                viewModel.executeAction(HomeAction.GetTrendingAll)
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Hi, Riju \uD83D\uDC4B\uD83C\uDFFC",
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Experience the Eternity",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                }

                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(54.dp),
                    model = "https://image.tmdb.org/t/p/original${state.popularMovies.data?.get(0)?.backdrop_path}",
                    contentDescription = state.popularMovies.data?.get(0)?.title,
                    contentScale = ContentScale.Crop,
                )
            }


            MediaTypeList(state.mediaType.data?:MediaType.All){
                viewModel.executeAction(HomeAction.ChangeMediaType(it))
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {


            }

            state.trendingAll.data?.let {
                TrendingMovieBannerPager(it.subList(0, 8),pagerState)
            }


            Text(
                text = "Find now",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 13.dp, top = 10.dp)
            )

            HomeGenreList()



            when(state.mediaType.data){
                MediaType.All->{

                    if (state.trendingMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetTrendingMovies)
                    if (state.trendingTv.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTrendingTv)
                    if (state.trendingPeople.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTrendingPeople)

                    HomeMoviesList(state.trendingMovies, title = "Trending movies")

                    HomeMoviesList(state.trendingTv, title = "Trending tv series")

                    HomeMoviesList(state.trendingPeople, title = "Trending people")

                }
                MediaType.Movies->{
                    if (state.popularMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetPopularMovies)
                    if (state.topRatedMovies.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTopRatedMovies)
                    if (state.upComingMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetUpComingMovies)

                    HomeMoviesList(state.trendingMovies, title = "Trending movies")


                    HomeMoviesList(state.popularMovies, title = "Popular movies")

                    HomeMoviesList(state.topRatedMovies, title = "Top rated movies")

                    HomeMoviesList(state.upComingMovies, title = "Upcoming movies")
                }
                MediaType.Tv->{
                    if (state.onTheAirTv.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetOnTheAirTv)
                    if (state.topRatedTv.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetTopRatedTv)
                    if (state.popularTv.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetPopularTv)


                    HomeMoviesList(state.onTheAirTv, title = "On the air tv series")
                    HomeMoviesList(state.trendingTv, title = "Trending tv series")
                    HomeMoviesList(state.topRatedTv, title = "Top rated tv series")
                    HomeMoviesList(state.popularTv, title = "Popular tv series")

                }
                MediaType.People ->{
                    HomeMoviesList(state.trendingPeople, title = "Trending people")

                }
                else -> {}
            }


        }


    }

}
