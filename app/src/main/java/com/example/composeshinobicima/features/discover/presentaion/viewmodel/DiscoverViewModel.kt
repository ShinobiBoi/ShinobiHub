package com.example.composeshinobicima.features.discover.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class DiscoverViewModel :MVIBaseViewModel<DiscoverAction,DiscoverResult,DiscoverViewState>() {
    override val defaultViewState: DiscoverViewState
        get() = DiscoverViewState()

    override fun handleAction(action: DiscoverAction): Flow<DiscoverResult> = flow {

        when (action) {

            is DiscoverAction.GetDiscoverMovie -> {
                //handleGetGenreList(this)

            }

            is DiscoverAction.GetDiscoverTv -> {

            }


            is DiscoverAction.ChangeMediaType -> {

            }

            is DiscoverAction.GetGenreList -> {

            }

            is DiscoverAction.ToggleGenre -> {

            }
        }

    }
}

//    private suspend fun handleGetGenreList(flowCollector: FlowCollector<DiscoverResult>) {
//
//        flowCollector.emit(DiscoverResult.GenreList(CommonViewState(isLoading = true)))
//
//        when(val dataState = getGenreListUseCase()){
//
//            is DataState.Success->{
//                flowCollector.emit(FindResult.GenreList(CommonViewState(data = dataState.data)))
//
//            }
//            is DataState.Error->{
//                flowCollector.emit(FindResult.GenreList(CommonViewState(errorThrowable = dataState.throwable)))
//            }
//            is DataState.Empty ->{
//                flowCollector.emit(FindResult.GenreList(CommonViewState(isEmpty = true)))
//            }
//            else ->{}
//        }
//
//    }
//}