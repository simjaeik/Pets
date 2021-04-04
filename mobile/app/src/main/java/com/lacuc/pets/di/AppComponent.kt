package com.lacuc.pets.di

import com.lacuc.pets.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>

}