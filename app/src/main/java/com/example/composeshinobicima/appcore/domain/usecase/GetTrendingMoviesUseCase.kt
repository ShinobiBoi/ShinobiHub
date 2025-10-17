package com.example.composeshinobicima.appcore.domain.usecase

import com.example.composeshinobicima.appcore.domain.repo.SharedRepo
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(private val sharedRepo: SharedRepo) {
    suspend operator fun invoke(page: Int) = sharedRepo.getTrendingMovies(page)
}