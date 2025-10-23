package com.example.composeshinobicima.features.discover.domain.usecase

import com.example.composeshinobicima.features.discover.domain.repo.DiscoverRepo
import com.example.composeshinobicima.features.find.domain.repo.FindRepo
import javax.inject.Inject

class GetDiscoverMovieUseCase @Inject constructor(private val discoverRepo: DiscoverRepo) {
    suspend operator fun invoke(genreId: String) = discoverRepo.getMovieDiscover(genreId)
}