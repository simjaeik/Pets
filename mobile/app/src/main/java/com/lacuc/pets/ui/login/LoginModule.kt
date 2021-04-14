package com.lacuc.pets.ui.login

import com.lacuc.pets.ui.login.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun getSignUpFragment(): SignUpFragment

}