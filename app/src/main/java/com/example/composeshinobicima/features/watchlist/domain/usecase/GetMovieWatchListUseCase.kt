package com.example.composeshinobicima.features.watchlist.domain.usecase

import com.example.composeshinobicima.features.watchlist.domain.repo.WatchListRepo
import javax.inject.Inject

class GetMovieWatchListUseCase  @Inject constructor(private val watchListRepo: WatchListRepo) {
    suspend operator fun invoke(accountId: Int, sessionId: String) = watchListRepo.getMovieWatchlist(accountId,sessionId)
}