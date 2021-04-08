package com.lacuc.pets.di

import com.lacuc.pets.MainActivity
import com.lacuc.pets.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}