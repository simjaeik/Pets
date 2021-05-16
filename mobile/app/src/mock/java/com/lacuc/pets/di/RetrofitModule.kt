package com.lacuc.pets.di

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import com.lacuc.pets.data.TokenResponse
import com.lacuc.pets.data.animal.AnimalService
import com.lacuc.pets.data.group.GroupService
import com.lacuc.pets.data.login.LoginService
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
        .baseUrl("http://203.247.166.230:8080/")
        .addCallAdapterFactory(ResultCallAdapter.Factory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Reusable
    fun provideLoginService() = object : LoginService {
        override suspend fun signIn(email: String, password: String): Result<TokenResponse> {
            return Result.Success(TokenResponse("fake token"))
        }

        override suspend fun isEmailExist(email: String): Result<SimpleBoolResult> {
            return Result.Success(SimpleBoolResult(false))
        }

        override suspend fun signUp(signUpParams: Map<String, String>): Result<SimpleBoolResult> {
            return Result.Success(SimpleBoolResult(true))
        }

        override suspend fun isNickNameExist(nickName: String): Result<SimpleBoolResult> {
            return Result.Success(SimpleBoolResult(false))
        }
    }

    @Provides
    @Reusable
    fun provideGroupService(retrofit: Retrofit): GroupService =
        retrofit.create(GroupService::class.java)

    @Provides
    @Reusable
    fun provideAnimalService(retrofit: Retrofit): AnimalService =
        retrofit.create(AnimalService::class.java)

}