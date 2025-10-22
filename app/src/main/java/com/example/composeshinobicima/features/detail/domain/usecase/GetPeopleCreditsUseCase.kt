package com.example.composeshinobicima.features.detail.domain.usecase

import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import javax.inject.Inject

class GetPeopleCreditsUseCase @Inject constructor(private val detailRepo: DetailRepo) {
    suspend operator fun invoke(personId: Int) = detailRepo.getPeopleCredits(personId)
}