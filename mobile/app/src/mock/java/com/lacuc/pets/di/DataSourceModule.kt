package com.lacuc.pets.di

import com.lacuc.pets.data.animal.AnimalDataSource
import com.lacuc.pets.data.animal.FakeAnimalRemoteDataSource
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

    @Binds
    @Singleton
    abstract fun bindsFakeAnimalRemoteDataSource(dataSource: FakeAnimalRemoteDataSource): AnimalDataSource

}