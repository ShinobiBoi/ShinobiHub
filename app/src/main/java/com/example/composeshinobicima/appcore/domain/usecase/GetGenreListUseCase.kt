package com.example.composeshinobicima.appcore.domain.usecase

import com.example.composeshinobicima.appcore.domain.repo.SharedRepo
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(private val sharedRepo: SharedRepo) {
    suspend operator fun invoke() = sharedRepo.getGenreList()
}