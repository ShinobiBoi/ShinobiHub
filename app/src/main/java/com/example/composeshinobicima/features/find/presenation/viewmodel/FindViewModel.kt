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
import dagger.hilt.android.lifecycle.HiltViewModel
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

    ) : MVIBaseViewModel<FindAction, FindResult, FindViewState>() {
    override val defaultViewState: FindViewState
        get() = FindViewState()

    override fun handleAction(action: FindAction): Flow<FindResult> = flow {


        when (action) {

            is FindAction.ChangeMediaType -> {
                emit(FindResult.Type(CommonViewState(data = action.type)))
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

