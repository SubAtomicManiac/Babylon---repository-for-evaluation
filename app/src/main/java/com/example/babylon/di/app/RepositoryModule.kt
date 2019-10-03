package com.example.babylon.di.app

import com.example.babylon.net.TypicodeService
import com.example.babylon.repository.Repository
import com.example.babylon.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(typicodeService: TypicodeService) : Repository{
        return RepositoryImpl(typicodeService)
    }



}