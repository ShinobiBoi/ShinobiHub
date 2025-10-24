package com.example.composeshinobicima.features.login.domain.usecase

import com.example.composeshinobicima.features.login.data.model.session.SessionRequest
import com.example.composeshinobicima.features.login.domain.repo.LoginRepo
import javax.inject.Inject

class CreateTokenUseCase @Inject constructor(private val loginRepo: LoginRepo) {
    suspend operator fun invoke() = loginRepo.createToken()
}