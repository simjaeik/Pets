package com.lacuc.pets.di

import android.app.Application
import android.content.Context
import com.lacuc.pets.App
import com.lacuc.pets.data.FakeGroupRepository
import com.lacuc.pets.data.GroupRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class, ViewModelModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideApp(app: App): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun provideContext(app: App): Context

    @Binds
    @Singleton
    abstract fun bindsFakeGroupRepository(repository: FakeGroupRepository): GroupRepository

}