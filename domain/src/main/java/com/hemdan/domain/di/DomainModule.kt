package com.hemdan.domain.di

import com.hemdan.domain.DefaultDispatcherProvider
import com.hemdan.domain.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun provideDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}