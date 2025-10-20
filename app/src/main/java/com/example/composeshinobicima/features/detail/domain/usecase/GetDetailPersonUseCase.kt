package com.example.composeshinobicima.features.detail.domain.usecase

import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class GetDetailPersonUseCase @Inject constructor(private val detailRepo: DetailRepo) {
    suspend operator fun invoke(personId: Int) = detailRepo.getDetailPerson(personId)
}