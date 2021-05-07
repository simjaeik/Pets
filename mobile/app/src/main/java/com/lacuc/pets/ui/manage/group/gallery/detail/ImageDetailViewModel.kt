package com.lacuc.pets.ui.manage.group.gallery.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.group.entity.GroupImage
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor() : ViewModel() {
    val image = MutableLiveData<GroupImage>()

    fun setImage(groupImage: GroupImage) {
        image.value = groupImage
    }
}