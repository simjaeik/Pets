package com.lacuc.pets.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.ui.animal.choose.ChooseAnimalViewModel
import com.lacuc.pets.ui.group.add.AddGroupViewModel
import com.lacuc.pets.ui.group.choose.ChooseGroupViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(AddGroupViewModel::class)
    abstract fun bindAddGroupViewModel(viewModel: AddGroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseAnimalViewModel::class)
    abstract fun bindChooseAnimalViewModel(viewModel: ChooseAnimalViewModel): ViewModel
}