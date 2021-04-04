package com.lacuc.pets.di

import com.lacuc.pets.data.FakeGroupRepository
import com.lacuc.pets.data.GroupRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsFakeGroupRepository(repository: FakeGroupRepository): GroupRepository

}