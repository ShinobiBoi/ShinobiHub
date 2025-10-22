package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import android.util.Log
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.review.Review
import com.example.composeshinobicima.features.detail.data.model.video.VideoItem
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailMovieUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailPersonUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetDetailTvUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetMovieCreditsUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetMovieReviewsUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetMovieVideoUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetMoviesSimilarUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetPeopleCreditsUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetTvCreditsUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetTvReviewsUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetTvSimilarUseCase
import com.example.composeshinobicima.features.detail.domain.usecase.GetTvVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    val getDetailMovieUseCase: GetDetailMovieUseCase,
    val getDetailTvUseCase: GetDetailTvUseCase,
    val getDetailPersonUseCase: GetDetailPersonUseCase,
    val getMovieVideoUseCase: GetMovieVideoUseCase,
    val getTvVideoUseCase: GetTvVideoUseCase,
    val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    val getTvCreditsUseCase: GetTvCreditsUseCase,
    val getPeopleCreditsUseCase: GetPeopleCreditsUseCase,
    val getMoviesSimilarUseCase: GetMoviesSimilarUseCase,
    val getTvSimilarUseCase: GetTvSimilarUseCase,
    val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    val getTvReviewsUseCase: GetTvReviewsUseCase
) : MVIBaseViewModel<DetailActions, DetailResults, DetailViewState>() {

    override val defaultViewState: DetailViewState
        get() = DetailViewState()

    override fun handleAction(action: DetailActions): Flow<DetailResults> = flow {

        when (action) {

            is DetailActions.SwitchTab ->{
                emit(DetailResults.SwitchTab(CommonViewState(data = action.tab)))
            }

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

            is DetailActions.GetMovieVideo ->{
                handleGetVideo(this){
                    getMovieVideoUseCase(action.movieId)
                }
            }

            is DetailActions.GetTvVideo ->{
                handleGetVideo(this){
                    getTvVideoUseCase(action.seriesId)
                }
            }

            is DetailActions.GetMovieCredits->{

                handleGetCredits(this){
                    getMovieCreditsUseCase(action.movieId)
                }
            }

            is DetailActions.GetTvCredits->{
                handleGetCredits(this){
                    getTvCreditsUseCase(action.seriesId)
                }
            }
            is DetailActions.GetPeopleCredits->{
                handleGetPeopleCredits(this){
                    getPeopleCreditsUseCase(action.personId)
                }
            }

            is DetailActions.GetMovieSimilar->{
                handleGetSimilar(this){
                    getMoviesSimilarUseCase(action.movieId)
                }
            }

            is DetailActions.GetTvSimilar->{
                handleGetSimilar(this){
                    getTvSimilarUseCase(action.seriesId)
                }
            }
            is DetailActions.GetMovieReviews -> {

                handleGetReviews(this){
                    getMovieReviewsUseCase(action.movieId)
                }


            }

            is DetailActions.GetTvReviews ->{
                handleGetReviews(this){
                    getTvReviewsUseCase(action.seriesId)
                }

            }




            else -> {}

        }

    }

    private suspend fun handleGetPeopleCredits(
        flowCollector: FlowCollector<DetailResults>,
        getPeopleCredits: suspend () -> DataState<List<MediaItem>>
    ) {
        flowCollector.emit(DetailResults.Loading(true))



        when(val result=getPeopleCredits()){

            is DataState.Success -> flowCollector.emit(
                DetailResults.PeopleCreditsLoad(CommonViewState(data = result.data, isSuccess = true))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.PeopleCreditsLoad(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.PeopleCreditsLoad(CommonViewState(isEmpty = true))
            )
            else -> {}


        }



        flowCollector.emit(DetailResults.Loading(false))

    }

    private suspend fun handleGetReviews(
        flowCollector: FlowCollector<DetailResults>,
        getReviews:  suspend () -> DataState<List<Review>>
    ) {
        flowCollector.emit(DetailResults.Loading(true))

        when(val result=getReviews()){

            is DataState.Success -> flowCollector.emit(
                DetailResults.ReviewsLoad(CommonViewState(data = result.data, isSuccess = true))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.ReviewsLoad(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.ReviewsLoad(CommonViewState(isEmpty = true))
            )
            else -> {}


        }


        flowCollector.emit(DetailResults.Loading(false))


    }

    private suspend fun handleGetSimilar(
        flowCollector: FlowCollector<DetailResults>,
        getSimilar: suspend () -> DataState<List<MediaItem>>
    ) {
        flowCollector.emit(DetailResults.Loading(true))


        when(val result=getSimilar()){

            is DataState.Success -> flowCollector.emit(
                DetailResults.SimilarLoad(CommonViewState(data = result.data, isSuccess = true))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.SimilarLoad(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.SimilarLoad(CommonViewState(isEmpty = true))
            )
            else -> {}


        }


        flowCollector.emit(DetailResults.Loading(false))

    }


    private suspend fun handleGetCredits(
        flowCollector: FlowCollector<DetailResults>,
        getCredits: suspend () -> DataState<CreditsResponse>
    ) {
        flowCollector.emit(DetailResults.Loading(true))

        when(val result=getCredits()){

            is DataState.Success -> flowCollector.emit(
                DetailResults.CreditsLoad(CommonViewState(data = result.data, isSuccess = true))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.CreditsLoad(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.CreditsLoad(CommonViewState(isEmpty = true))
            )
            else -> {}


        }


        flowCollector.emit(DetailResults.Loading(false))


    }

    private suspend fun handleGetVideo(
        flowCollector: FlowCollector<DetailResults>,
         getVideo: suspend () -> DataState<List<VideoItem>>
    ) {


        flowCollector.emit(DetailResults.Loading(true))

        when(val result=getVideo()){

            is DataState.Success -> flowCollector.emit(
                DetailResults.VideoList(CommonViewState(data = result.data, isSuccess = true))
            )
            is DataState.Error -> flowCollector.emit(
                DetailResults.VideoList(CommonViewState(errorThrowable = result.throwable))
            )
            is DataState.Empty -> flowCollector.emit(
                DetailResults.VideoList(CommonViewState(isEmpty = true))
            )
            else -> {}


        }
        flowCollector.emit(DetailResults.Loading(false))


    }

    private suspend fun handleGetDetailMovie(
        flowCollector: FlowCollector<DetailResults>,
        getMediaItem: suspend () -> DataState<DetailMediaItem>
    ) {
        flowCollector.emit(DetailResults.Loading(true))
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


        flowCollector.emit(DetailResults.Loading(false))



    }

}