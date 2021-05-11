package com.lacuc.pets.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.ui.login.signup.SignUpViewModel
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.ui.manage.animal.add.AddAnimalViewModel
import com.lacuc.pets.ui.manage.animal.choose.ChooseAnimalViewModel
import com.lacuc.pets.ui.manage.animal.detail.AnimalDetailViewModel
import com.lacuc.pets.ui.manage.animal.detail.medical.AddMedicalViewModel
import com.lacuc.pets.ui.manage.animal.detail.memo.AddMemoViewModel
import com.lacuc.pets.ui.manage.group.choose.ChooseGroupViewModel
import com.lacuc.pets.ui.manage.group.gallery.GalleryViewModel
import com.lacuc.pets.ui.manage.group.gallery.detail.ImageDetailViewModel
import com.lacuc.pets.ui.manage.group.gallery.save.SaveImageViewModel
import com.lacuc.pets.ui.manage.group.info.UserProfileViewModel
import com.lacuc.pets.ui.manage.group.item.ItemListViewModel
import com.lacuc.pets.ui.manage.group.member.ManageMemberViewModel
import com.lacuc.pets.ui.manage.group.save.SaveGroupViewModel
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
    @ViewModelKey(ManageViewModel::class)
    abstract fun bindManageViewModel(viewModel: ManageViewModel): ViewModel

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
    @ViewModelKey(SaveGroupViewModel::class)
    abstract fun bindSaveGroupViewModel(viewModel: SaveGroupViewModel): ViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(viewModel: UserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManageMemberViewModel::class)
    abstract fun bindManageMemberViewModel(viewModel: ManageMemberViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun bindGalleryViewModel(viewModel: GalleryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SaveImageViewModel::class)
    abstract fun bindSaveImageViewModel(viewModel: SaveImageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailViewModel::class)
    abstract fun bindImageDetailViewModel(viewModel: ImageDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemListViewModel::class)
    abstract fun bindItemListViewModel(viewModel: ItemListViewModel): ViewModel
}