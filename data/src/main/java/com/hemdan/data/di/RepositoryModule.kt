package com.hemdan.data.di

import com.hemdan.data.repository.CardSubmitRepositoryImpl
import com.hemdan.domain.repository.CardSubmitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideVerifyCardRepository(verifyCardVerifyRepository: CardSubmitRepositoryImpl): CardSubmitRepository
}