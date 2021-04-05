package com.lacuc.pets.di

import com.lacuc.pets.data.group.FakeGroupRemoteDataSource
import com.lacuc.pets.data.group.GroupDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsFakeGroupRemoteDataSource(dataSource: FakeGroupRemoteDataSource): GroupDataSource

}