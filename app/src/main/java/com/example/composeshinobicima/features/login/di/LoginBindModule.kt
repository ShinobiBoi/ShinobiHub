package com.example.composeshinobicima.features.login.di

import com.example.composeshinobicima.features.login.data.remote.LoginRemoteClientImp
import com.example.composeshinobicima.features.login.data.repo.LoginRepoImp
import com.example.composeshinobicima.features.login.domain.remote.LoginRemoteClient
import com.example.composeshinobicima.features.login.domain.repo.LoginRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class LoginBindModule {


    @Binds
    abstract fun bindLoginRepo(loginRepoImp: LoginRepoImp): LoginRepo

    @Binds
    abstract fun bindLoginRemoteClient(loginRemoteClientImp: LoginRemoteClientImp):LoginRemoteClient

}