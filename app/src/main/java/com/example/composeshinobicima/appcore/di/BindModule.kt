package com.example.composeshinobicima.appcore.di

import com.example.composeshinobicima.appcore.data.remote.SharedRemoteClientImp
import com.example.composeshinobicima.appcore.data.repo.SharedRepoImp
import com.example.composeshinobicima.appcore.domain.remote.SharedRemoteClient
import com.example.composeshinobicima.appcore.domain.repo.SharedRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {


    @Binds
    abstract fun bindSharedRemoteClient(sharedRemoteClientImp: SharedRemoteClientImp): SharedRemoteClient

    @Binds
    abstract fun bindSharedRepo(sharedRepoImp: SharedRepoImp): SharedRepo

}