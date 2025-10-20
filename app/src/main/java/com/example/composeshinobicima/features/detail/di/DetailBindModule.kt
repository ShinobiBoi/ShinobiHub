package com.example.composeshinobicima.features.detail.di

import com.example.composeshinobicima.features.detail.data.remote.DetailRemoteClientImp
import com.example.composeshinobicima.features.detail.data.repo.DetailRepoImp
import com.example.composeshinobicima.features.detail.domain.remote.DetailRemoteClient
import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DetailBindModule {


    @Binds
    abstract fun bindDetailRemoteClient(detailRemoteClientImp: DetailRemoteClientImp): DetailRemoteClient

    @Binds
    abstract fun bindDetailRepo(detailRepoImp: DetailRepoImp): DetailRepo




}