package com.example.composeshinobicima.features.find.di

import com.example.composeshinobicima.features.find.data.remote.FindRemoteClientImp
import com.example.composeshinobicima.features.find.data.repo.FindRepoImp
import com.example.composeshinobicima.features.find.domain.remote.FindRemoteClient
import com.example.composeshinobicima.features.find.domain.repo.FindRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class FindBindModule {

    @Binds
    abstract fun bindFindRemoteClient(findRemoteClientImp: FindRemoteClientImp):FindRemoteClient


    @Binds
    abstract fun bindFindRepo(findRepoImp: FindRepoImp):FindRepo

}