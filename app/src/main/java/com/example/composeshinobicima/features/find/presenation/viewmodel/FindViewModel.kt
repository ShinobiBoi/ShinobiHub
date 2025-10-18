package com.example.composeshinobicima.features.find.presenation.viewmodel

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingAllUseCase
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingMoviesUseCase
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingPeopleUseCase
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingTvUseCase
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.features.find.domain.usecase.SearchMovieUseCase
import com.example.composeshinobicima.features.find.domain.usecase.SearchMultiUseCase
import com.example.composeshinobicima.features.find.domain.usecase.SearchPeopleUseCase
import com.example.composeshinobicima.features.find.domain.usecase.SearchTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class FindViewModel @Inject constructor(
    val getTrendingAllUseCase: GetTrendingAllUseCase,
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    val getTrendingTvUseCase: GetTrendingTvUseCase,
    val getTrendingPeopleUseCase: GetTrendingPeopleUseCase,
    val searchMultiUseCase: SearchMultiUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val searchTvUseCase: SearchTvUseCase,
    val searchPeopleUseCase: SearchPeopleUseCase
    ) : MVIBaseViewModel<FindAction, FindResult, FindViewState>() {
    override val defaultViewState: FindViewState
        get() = FindViewState()

    override fun handleAction(action: FindAction): Flow<FindResult> = flow {


        when (action) {

            is FindAction.ChangeMediaType -> {
                emit(FindResult.Type(CommonViewState(data = action.type)))
            }
            is FindAction.ChangeQuery -> {
                emit(FindResult.QueryChanged(CommonViewState(data = action.query)))
            }

            is FindAction.GetTrendingAll -> {

                handleNewMedia(this, getTrendingAllUseCase(1))

            }

            is FindAction.GetTrendingMovies -> {
                handleNewMedia(this, getTrendingMoviesUseCase(1))

            }

            is FindAction.GetTrendingTv -> {

                handleNewMedia(this, getTrendingTvUseCase(1))
            }

            is FindAction.GetTrendingPeople -> {
                handleNewMedia(this, getTrendingPeopleUseCase(1))
            }

            is FindAction.SearchMulti -> {
                handleNewMedia(this, searchMultiUseCase(action.query, 1))

            }
            is FindAction.SearchMovie -> {
                handleNewMedia(this, searchMovieUseCase(action.query, 1))

            }
            is FindAction.SearchTv -> {
                handleNewMedia(this, searchTvUseCase(action.query, 1))

            }
            is FindAction.SearchPeople -> {
                handleNewMedia(this, searchPeopleUseCase(action.query, 1))

            }


        }

    }



}

private suspend fun handleNewMedia(
    flowCollector: FlowCollector<FindResult>,
    dataState: DataState<List<MediaItem>>
) {
    flowCollector.emit(FindResult.MediaLoaded(MediaViewState(isLoading = true)))


    when (dataState) {
        is DataState.Success -> flowCollector.emit(
            FindResult.MediaLoaded(MediaViewState(data = dataState.data, isSuccess = true))
        )
        is DataState.Error -> flowCollector.emit(
            FindResult.MediaLoaded(MediaViewState(errorThrowable = dataState.throwable))
        )
        is DataState.Empty -> flowCollector.emit(
            FindResult.MediaLoaded(MediaViewState(isEmpty = true))
        )
        else -> {}
    }

}

