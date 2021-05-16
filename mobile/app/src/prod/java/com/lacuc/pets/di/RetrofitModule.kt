package com.lacuc.pets.di

import com.lacuc.pets.data.animal.AnimalService
import com.lacuc.pets.data.group.GroupService
import com.lacuc.pets.data.login.LoginService
import com.lacuc.pets.util.retrofit.AddTokenInterceptor
import com.lacuc.pets.util.retrofit.ResultCallAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class RetrofitModule {

    @Binds
    abstract fun bindAddTokenInterceptor(interceptor: AddTokenInterceptor): Interceptor

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(addTokenInterceptor: AddTokenInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                .addInterceptor(addTokenInterceptor)
                .build()

        @Provides
        @Singleton
        fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
//            .baseUrl("http://203.247.166.230:8080/")
            .baseUrl("http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/")
            .addCallAdapterFactory(ResultCallAdapter.Factory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
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

    }
}