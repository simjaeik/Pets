package com.lacuc.pets.ui.login

import com.lacuc.pets.ui.login.signin.SignInFragment
import com.lacuc.pets.ui.login.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun getSignInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun getSignUpFragment(): SignUpFragment

}