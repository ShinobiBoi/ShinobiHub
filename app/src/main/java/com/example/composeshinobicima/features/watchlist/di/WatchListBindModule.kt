package com.example.composeshinobicima.features.watchlist.di

import com.example.composeshinobicima.features.watchlist.data.remote.WatchListRemoteClientImp
import com.example.composeshinobicima.features.watchlist.data.repo.WatchListRepoImp
import com.example.composeshinobicima.features.watchlist.domain.remote.WatchListRemoteClient
import com.example.composeshinobicima.features.watchlist.domain.repo.WatchListRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class WatchListBindModule {

    @Binds
    abstract fun bindWatchListRemoteClient(client: WatchListRemoteClientImp): WatchListRemoteClient

    @Binds
    abstract fun bindWatchListRepo(repo: WatchListRepoImp): WatchListRepo



}