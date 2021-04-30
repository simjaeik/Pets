package com.lacuc.pets.di

import com.lacuc.pets.data.animal.AnimalService
import com.lacuc.pets.data.group.GroupService
import com.lacuc.pets.data.login.LoginService
import com.lacuc.pets.data.post.PostService
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
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Reusable
    fun provideGroupService(retrofit: Retrofit): GroupService =
        retrofit.create(GroupService::class.java)

    @Provides
    @Reusable
    fun provideAnimalService(retrofit: Retrofit): AnimalService =
        retrofit.create(AnimalService::class.java)

    @Provides
    @Reusable
    fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)
}