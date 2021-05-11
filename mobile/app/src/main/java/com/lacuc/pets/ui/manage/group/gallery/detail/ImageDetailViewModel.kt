package com.lacuc.pets.ui.manage.group.gallery.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.GroupImage
import com.lacuc.pets.domain.image.GetGroupImageUseCase
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val getGroupImageUseCase: GetGroupImageUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = -1

    var iid = -1

    val image = MutableLiveData<GroupImage>()

    fun loadImage() {
        viewModelScope.launch {
            val result = getGroupImageUseCase(gid, iid)

            when (result) {
                is Result.Success -> result.body?.let { image.value = it }
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
}