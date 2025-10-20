package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailMovieUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailPersonUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailTvUseCase
import com.example.composeshinobicima.features.home.presentaion.viewmodel.HomeResult
import com.example.composeshinobicima.features.home.presentaion.viewmodel.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    val getDetailMovieUseCase: GetDetailMovieUseCase,
    val getDetailTvUseCase: GetDetailTvUseCase,
    val getDetailPersonUseCase: GetDetailPersonUseCase
) : MVIBaseViewModel<DetailActions, DetailResults, DetailViewState>() {

    override val defaultViewState: DetailViewState
        get() = DetailViewState()

    override fun handleAction(action: DetailActions): Flow<DetailResults> = flow {

        when (action) {
            is DetailActions.GetDetailMovie -> {
                handleGetDetailMovie(this){
                    getDetailMovieUseCase(action.movieId)
                }

            }
            is DetailActions.GetDetailTv ->{
                handleGetDetailMovie(this){
                    getDetailTvUseCase(action.seriesId)
                }
            }
            is DetailActions.GetDetailPerson->{
                handleGetDetailMovie(this){
                    getDetailPersonUseCase(action.personId)
                }
            }

            else -> {}

        }

    }

    private suspend fun handleGetDetailMovie(
        flowCollector: FlowCollector<DetailResults>,
        getMediaItem: suspend () -> DataState<DetailMediaItem>
    ) {
        flowCollector.emit(DetailResults.DetailMediaLoaded(CommonViewState(isLoading = true)))

        when(val result=getMediaItem()){
            is DataState.Success -> flowCollector.emit(
                DetailResults.DetailMediaLoaded(CommonViewState(data = result.data()))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.DetailMediaLoaded(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.DetailMediaLoaded(CommonViewState(isEmpty = true))
            )
            else -> {}



        }





    }

}