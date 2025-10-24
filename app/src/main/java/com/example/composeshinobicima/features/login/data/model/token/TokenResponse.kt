package com.example.composeshinobicima.features.login.data.model.token

data class TokenResponse(
    val success:Boolean,
    val expires_at:String,
    val request_token:String
)
