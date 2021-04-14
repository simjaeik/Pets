package com.lacuc.pets.util

import androidx.lifecycle.MutableLiveData

var <T: Any> MutableLiveData<T>.safeValue: T
    get() {
        return this.value as T
    }
    set(value) {
        this.value = value
    }