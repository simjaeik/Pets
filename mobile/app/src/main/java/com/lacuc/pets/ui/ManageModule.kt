package com.lacuc.pets.ui

import com.lacuc.pets.ui.manage.animal.add.AddAnimalFragment
import com.lacuc.pets.ui.manage.animal.choose.ChooseAnimalFragment
import com.lacuc.pets.ui.manage.animal.detail.AnimalDetailFragment
import com.lacuc.pets.ui.manage.animal.detail.medical.AddMedicalFragment
import com.lacuc.pets.ui.manage.animal.detail.memo.AddMemoFragment
import com.lacuc.pets.ui.manage.group.add.AddGroupFragment
import com.lacuc.pets.ui.manage.group.choose.ChooseGroupFragment
import com.lacuc.pets.ui.manage.group.info.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ManageModule {
    @ContributesAndroidInjector
    abstract fun getChooseGroupFragment(): ChooseGroupFragment

    @ContributesAndroidInjector
    abstract fun getAddGroupFragment(): AddGroupFragment

    @ContributesAndroidInjector
    abstract fun getChooseAnimalFragment(): ChooseAnimalFragment

    @ContributesAndroidInjector
    abstract fun getAddAnimalFragment(): AddAnimalFragment

    @ContributesAndroidInjector
    abstract fun getAnimalDetailFragment(): AnimalDetailFragment

    @ContributesAndroidInjector
    abstract fun getAddMedicalFragment(): AddMedicalFragment

    @ContributesAndroidInjector
    abstract fun getAddMemoFragment(): AddMemoFragment

    @ContributesAndroidInjector
    abstract fun getUserProfileFragment(): UserProfileFragment
}