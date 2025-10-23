package com.example.composeshinobicima.features.discover.di

import com.example.composeshinobicima.features.discover.data.remote.DiscoverRemoteClientImp
import com.example.composeshinobicima.features.discover.data.repo.DiscoverRepoImp
import com.example.composeshinobicima.features.discover.domain.remote.DiscoverRemoteClient
import com.example.composeshinobicima.features.discover.domain.repo.DiscoverRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DiscoverBindModule {



    @Binds
    abstract fun bindDiscoverRemote(discoverRemoteClientImp: DiscoverRemoteClientImp):DiscoverRemoteClient

    @Binds
    abstract fun bindDiscoverRepo(discoverRepoImp: DiscoverRepoImp):DiscoverRepo

}