package com.example.composeshinobicima.features.login.data.model.login

data class LoginResponse(
    val success:Boolean,
    val expires_at :String,
    val request_token:String,
)