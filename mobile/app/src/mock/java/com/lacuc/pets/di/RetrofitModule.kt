package com.lacuc.pets.di

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.util.retrofit.ResultCallAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("") // TODO: 2021-04-16 도메인 입력
        .addCallAdapterFactory(ResultCallAdapter.Factory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Reusable
    fun provideLoginService() = object : LoginService {
        override fun signIn(param: Map<String, String>): Boolean = true

        override fun checkUserInfo(userID: String, userPW: String) {
            TODO("Not yet implemented")
        }

        override fun isIDExist(id: Int) {
            TODO("Not yet implemented")
        }

        override fun signUp(signUpParams: Map<String, String>) {
            TODO("Not yet implemented")
        }
    }
}