package com.lacuc.pets

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
}