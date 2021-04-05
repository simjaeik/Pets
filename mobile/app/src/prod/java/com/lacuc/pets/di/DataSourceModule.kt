package com.lacuc.pets.di

import com.lacuc.pets.data.group.GroupDataSource
import com.lacuc.pets.data.group.GroupRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsGroupRemoteDataSource(dataSource: GroupRemoteDataSource): GroupDataSource

}