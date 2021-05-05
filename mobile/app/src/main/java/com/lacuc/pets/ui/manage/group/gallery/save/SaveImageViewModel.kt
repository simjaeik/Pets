package com.lacuc.pets.ui.manage.group.gallery.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SaveImageViewModel @Inject constructor() : ViewModel() {

    val image = MutableLiveData("")

    val tagString = MutableLiveData("")

    // TODO: 2021-05-05 임시로 태그 리스트를 쓰지만 서버에서 태그 목록을 받거나, Room을 쓰면 좋을듯?
    var tagSet = setOf("강아지", "고양이", "호랑이", "기린")
        private set

    val addedTagList = mutableListOf<String>()

    fun setImage(dataString: String?) {
        image.value = dataString
    }

    fun addTag(tag: String): Boolean {
        tagString.value = ""
        if (tag.isNotEmpty() && tag !in addedTagList) {
            addedTagList.add(tag)
            tagSet = tagSet + tag
            return true
        }
        return false
    }

    fun removeTag(tag: String): Boolean = addedTagList.remove(tag)
}