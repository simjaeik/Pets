package com.lacuc.pets.di

import com.lacuc.pets.data.LoginService
import dagger.Module
import dagger.Provides
import dagger.Reusable

// TODO: 2021-04-04 레트로핏으로 객체를 제공할 수 있도록 변경해야 함. 
@Module
class RetrofitModule {
    @Provides
    @Reusable
    fun provideLoginService() = object : LoginService {
        override fun signUp(param: Map<String, String>): Boolean {
            TODO("Not yet implemented")
        }

        override fun signIn(param: Map<String, String>): Boolean {
            TODO("Not yet implemented")
        }
    }
}