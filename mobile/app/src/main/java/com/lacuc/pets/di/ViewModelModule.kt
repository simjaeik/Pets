package com.lacuc.pets.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.ui.group.ChooseGroupViewModel
import com.lacuc.pets.ui.login.signup.SignUpViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseGroupViewModel::class)
    abstract fun bindChooseGroupViewModel(viewModel: ChooseGroupViewModel): ViewModel
}