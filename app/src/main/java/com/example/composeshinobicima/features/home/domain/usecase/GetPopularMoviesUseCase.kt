package com.example.composeshinobicima.features.home.domain.usecase

import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    suspend operator fun invoke(page: Int) = homeRepo.getPopularMovies(page)
}