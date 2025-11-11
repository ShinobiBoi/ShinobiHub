package com.example.composeshinobicima.features.profile.domain.usecase

import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import com.example.composeshinobicima.features.profile.data.model.DeleteSessionRequest
import com.example.composeshinobicima.features.profile.domain.repo.ProfileRepo
import javax.inject.Inject

class DeleteSessionUseCase @Inject constructor(private val profileRepo: ProfileRepo) {
    suspend operator fun invoke(body:DeleteSessionRequest) = profileRepo.deleteSession(body)
}