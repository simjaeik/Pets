package com.lacuc.pets

import com.lacuc.pets.ui.animal.add.AddAnimalFragment
import com.lacuc.pets.ui.animal.choose.ChooseAnimalFragment
import com.lacuc.pets.ui.animal.detail.AnimalDetailAddMedicalFragment
import com.lacuc.pets.ui.animal.detail.AnimalDetailAddMemoFragment
import com.lacuc.pets.ui.animal.detail.AnimalDetailFragment
import com.lacuc.pets.ui.group.add.AddGroupFragment
import com.lacuc.pets.ui.group.choose.ChooseGroupFragment
import com.lacuc.pets.ui.login.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun getSignUpFragment(): SignUpFragment

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
    abstract fun getAnimalDetailAddMedicalFragment(): AnimalDetailAddMedicalFragment

    @ContributesAndroidInjector
    abstract fun getAnimalDetailAddMemoFragment(): AnimalDetailAddMemoFragment
}