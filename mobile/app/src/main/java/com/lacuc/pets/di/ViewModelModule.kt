package com.lacuc.pets.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.ui.login.signup.SignUpViewModel
import com.lacuc.pets.ui.manage.animal.add.AddAnimalViewModel
import com.lacuc.pets.ui.manage.animal.choose.ChooseAnimalViewModel
import com.lacuc.pets.ui.manage.animal.detail.AnimalDetailViewModel
import com.lacuc.pets.ui.manage.animal.detail.medical.AddMedicalViewModel
import com.lacuc.pets.ui.manage.animal.detail.memo.AddMemoViewModel
import com.lacuc.pets.ui.manage.group.add.AddGroupViewModel
import com.lacuc.pets.ui.manage.group.choose.ChooseGroupViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(AddAnimalViewModel::class)
    abstract fun bindAddAnimalViewModel(viewModel: AddAnimalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimalDetailViewModel::class)
    abstract fun bindAnimalDetailViewModel(viewModel: AnimalDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddMedicalViewModel::class)
    abstract fun bindAddMedicalViewModel(viewModel: AddMedicalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddMemoViewModel::class)
    abstract fun bindAddMemoViewModel(viewModel: AddMemoViewModel): ViewModel
}