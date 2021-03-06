package com.lacuc.pets.ui.manage.group.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.image.GetGroupImagesUseCase
import com.lacuc.pets.domain.image.ImageItem
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val getGroupImagesUseCase: GetGroupImagesUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""

    val images = MutableLiveData<List<ImageItem>>()

    val imageClickEvent = SingleLiveEvent<ImageItem>()

    fun loadImages() {
        viewModelScope.launch {
            val imageList = getGroupImagesUseCase(gid) { imageClickEvent.value = it }

            when (imageList) {
                is Result.Success -> imageList.body?.let { images.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${imageList.code} message: ${imageList.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(imageList.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

}