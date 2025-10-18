package com.example.composeshinobicima.features.find.domain.usecase

import com.example.composeshinobicima.features.find.domain.repo.FindRepo
import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class SearchMultiUseCase @Inject constructor(private val findRepo: FindRepo) {
    suspend operator fun invoke(query: String,page: Int) = findRepo.searchMulti(query,page)
}