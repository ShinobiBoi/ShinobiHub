package com.example.composeshinobicima.features.detail.domain.usecase

import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import javax.inject.Inject

class GetTvAccountStateUseCase @Inject constructor(
    private val detailRepo: DetailRepo
) {
    suspend operator fun invoke(tvId: Int, sessionId: String) =
        detailRepo.getTvAccountState(tvId, sessionId)
}