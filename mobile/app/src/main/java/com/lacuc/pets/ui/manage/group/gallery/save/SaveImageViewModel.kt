package com.lacuc.pets.ui.manage.group.gallery.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SaveImageViewModel @Inject constructor() : ViewModel() {
    val image = MutableLiveData("")

    fun setImage(dataString: String?) {
        image.value = dataString
    }
}