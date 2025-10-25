package com.example.composeshinobicima.features.favourite.domain.usecase

import com.example.composeshinobicima.features.favourite.domain.repo.FavouriteRepo
import javax.inject.Inject

class GetMovieFavouriteUseCase @Inject constructor(private val favouriteRepo: FavouriteRepo) {
    suspend operator fun invoke(accountId: Int, sessionId: String) = favouriteRepo.getMovieFavourite(accountId,sessionId)
}