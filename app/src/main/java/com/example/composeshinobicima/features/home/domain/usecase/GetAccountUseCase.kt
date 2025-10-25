package com.example.composeshinobicima.features.home.domain.usecase

import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    suspend operator fun invoke(sessionId: String) = homeRepo.getAccount(sessionId)
}