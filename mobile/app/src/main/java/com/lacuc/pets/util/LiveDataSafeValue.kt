package com.lacuc.pets.util

import androidx.lifecycle.MutableLiveData

val <T: Any> MutableLiveData<T>.safeValue: T
    get() = this.value as T