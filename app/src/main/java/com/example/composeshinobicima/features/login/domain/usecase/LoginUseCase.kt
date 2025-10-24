package com.example.composeshinobicima.features.login.domain.usecase

import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.domain.repo.LoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepo: LoginRepo) {
    suspend operator fun invoke(loginRequest: LoginRequest) = loginRepo.login(loginRequest)
}