package com.lacuc.pets.di

import android.app.Application
import android.content.Context
import com.lacuc.pets.App
import com.lacuc.pets.util.SingleLiveEvent
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class, ViewModelModule::class, RepositoryModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideApp(app: App): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun provideContext(app: App): Context

    companion object {
        @Provides
        @Singleton
        fun provideErrorEvent() = SingleLiveEvent<String>()
    }
}