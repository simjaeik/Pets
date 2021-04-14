package com.lacuc.pets.di

import com.lacuc.pets.ui.MainActivity
import com.lacuc.pets.ui.ManageModule
import com.lacuc.pets.ui.login.LoginActivity
import com.lacuc.pets.ui.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [ManageModule::class])
    abstract fun mainActivity(): MainActivity
}