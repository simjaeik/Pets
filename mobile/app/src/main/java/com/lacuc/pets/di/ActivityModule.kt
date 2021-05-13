package com.lacuc.pets.di

import com.lacuc.pets.ui.login.LoginActivity
import com.lacuc.pets.ui.login.LoginModule
import com.lacuc.pets.ui.manage.ManageActivity
import com.lacuc.pets.ui.manage.ManageModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun loginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ManageModule::class])
    abstract fun mainActivity(): ManageActivity
}