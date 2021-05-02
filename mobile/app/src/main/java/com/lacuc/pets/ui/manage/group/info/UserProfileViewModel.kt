package com.lacuc.pets.ui.manage.group.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding4.InitialValueObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    // TODO: 2021-05-02 val updateProfileUseCase
) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")

    val completeBtnEnable = MutableLiveData(false)

    fun updateProfile() {
        // TODO: 2021-05-02 call updateProfileUseCase
    }

    fun bindCompleteBtnEnable(
        nameObservable: InitialValueObservable<CharSequence>,
        emailObservable: InitialValueObservable<CharSequence>
    ): Disposable =
        Observable.combineLatest(nameObservable, emailObservable) { nameText, emailText ->
            nameText.isNotEmpty() && emailText.isNotEmpty()
        }.subscribe(completeBtnEnable::setValue)

}