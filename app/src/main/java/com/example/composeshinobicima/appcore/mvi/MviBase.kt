package com.example.composeshinobicima.appcore.mvi

import com.example.composeshinobicima.appcore.domain.model.MediaItem


/**
 * Interface definition of the Action(Intent in MVI)
 */
interface Action

/**
 * Interface definition of the ViewStare(Model in MVI)
 */
interface ViewState


/**
 * Interface definition of the Result that will be reduced to [ViewState]
 */
interface Result<VS : ViewState> {
    fun reduce(defaultState: VS, oldState: VS): VS
}

data class CommonViewState<T>(
    val isIdle: Boolean = false,
    val isEmpty: Boolean = false,
    val data: T? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isSuccess: Boolean = false,
    val errorThrowable: Throwable? = null,
) : ViewState

typealias MediaViewState = CommonViewState<List<MediaItem>>