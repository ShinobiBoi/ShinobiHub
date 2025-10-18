package com.example.composeshinobicima.features.find.domain.usecase

import com.example.composeshinobicima.features.find.domain.repo.FindRepo
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val findRepo: FindRepo) {
    suspend operator fun invoke(query: String,page: Int) = findRepo.searchMovie(query,page)
}