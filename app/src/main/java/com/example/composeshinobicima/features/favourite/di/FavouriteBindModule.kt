package com.example.composeshinobicima.features.favourite.di

import com.example.composeshinobicima.features.favourite.data.remote.FavouriteRemoteClientImp
import com.example.composeshinobicima.features.favourite.data.repo.FavouriteRepoImp
import com.example.composeshinobicima.features.favourite.domain.remote.FavouriteRemoteClient
import com.example.composeshinobicima.features.favourite.domain.repo.FavouriteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteBindModule {


    @Binds
    abstract fun bindFavouriteRemoteClient(clientImp: FavouriteRemoteClientImp):FavouriteRemoteClient

    @Binds
    abstract fun bindFavouriteRepo(repo: FavouriteRepoImp):FavouriteRepo

}