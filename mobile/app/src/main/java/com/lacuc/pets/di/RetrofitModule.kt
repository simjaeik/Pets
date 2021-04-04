package com.lacuc.pets.di

import com.lacuc.pets.data.LoginService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RetrofitModule {
    @Provides
    @Reusable
    fun provideLoginService() = object : LoginService {}
}