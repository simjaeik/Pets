package com.lacuc.pets.ui.manage.group.gallery.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.image.GetGroupImageUseCase
import com.lacuc.pets.domain.image.UpdateImageUseCase
import com.lacuc.pets.domain.image.UploadImageUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SaveImageViewModel @Inject constructor(
    private val getGroupImageUseCase: GetGroupImageUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val updateImageUseCase: UpdateImageUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var iid = -1

    var gid: Int = -1

    val image = MutableLiveData("")

    val tagString = MutableLiveData("")

    // TODO: 2021-05-05 임시로 태그 리스트를 쓰지만 서버에서 태그 목록을 받거나, Room을 쓰면 좋을듯?
    var tagSet = setOf("강아지", "고양이", "호랑이", "기린")
        private set

    val addedTagList = mutableListOf<String>()

    val completeEvent = SingleLiveEvent<Unit>()
    val updateEvent = SingleLiveEvent<Int>()

    private var isUpdate = false

    fun loadImage() {
        isUpdate = true
        viewModelScope.launch {
            val _image = getGroupImageUseCase(gid, iid)

            when (_image) {
                is Result.Success -> _image.body?.let {
                    image.value = it.url
                    addedTagList.addAll(it.tag.split(","))
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${_image.code} message: ${_image.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(_image.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }

    }

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

    fun saveImage() {
        viewModelScope.launch {
            val result = if (isUpdate) {
                updateImage()
            } else {
                uploadImage()
            }

            when (result) {
                is Result.Success -> {
                    if (isUpdate)
                        updateEvent.value = iid
                    else
                        completeEvent.value = Unit
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${result.code} message: ${result.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(result.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    private suspend fun updateImage() = updateImageUseCase(
        mapOf(
            "IID" to iid,
            "GID" to gid,
            "url" to image.safeValue,
            "tag" to addedTagList.joinToString(",")
        )
    )


    private suspend fun uploadImage() = uploadImageUseCase(
        mapOf(
            "GID" to gid,
            "url" to image.safeValue,
            "tag" to addedTagList.joinToString(",")
        )
    )

}