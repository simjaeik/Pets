package com.lacuc.pets.di

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.DefaultAnimalRepository
import com.lacuc.pets.data.group.DefaultGroupRepository
import com.lacuc.pets.data.group.GroupRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultGroupRepository(repository: DefaultGroupRepository): GroupRepository

    @Binds
    @Singleton
    abstract fun bindsDefaultAnimalRepository(repository: DefaultAnimalRepository): AnimalRepository
}