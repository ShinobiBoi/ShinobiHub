package com.example.composeshinobicima.features.home.presentaion.viewmodel


import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.Result

sealed class HomeResult : Result<HomeViewState> {


    data class Type(val state: CommonViewState<MediaType>) : HomeResult(){
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                mediaType = state
            )
        }

    }


    data class TrendingAllLoaded(val state: MediaViewState) : HomeResult(){
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                trendingAll = state
            )
        }

    }

    data class TrendingMoviesLoaded(val state: MediaViewState) : HomeResult(){
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                trendingMovies = state
            )
        }

    }

    data class TrendingTvLoaded(val state: MediaViewState) : HomeResult(){
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                trendingTv = state
            )
        }

    }

    data class TrendingPeopleLoaded(val state: MediaViewState) : HomeResult(){
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                trendingPeople = state
            )
        }

    }


    data class PopularMoviesLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                popularMovies = state
            )
        }
    }

    data class TopRatedMoviesLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                topRatedMovies = state
            )
        }
    }

    data class UpComingMoviesLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                upComingMovies = state
                )
        }
    }

    data class OnTheAirTvLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                onTheAirTv = state
            )
        }
    }


    data class PopularTvLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                popularTv = state
            )
        }
    }

    data class TopRatedTvLoaded(val state: MediaViewState) : HomeResult() {
        override fun reduce(defaultState: HomeViewState, oldState: HomeViewState): HomeViewState {
            return oldState.copy(
                topRatedTv = state
            )
        }
    }

}