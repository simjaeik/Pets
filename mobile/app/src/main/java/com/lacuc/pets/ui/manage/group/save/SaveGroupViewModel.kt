package com.lacuc.pets.ui.manage.group.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.domain.group.AddGroupUseCase
import com.lacuc.pets.domain.group.GetGroupUseCase
import com.lacuc.pets.domain.group.UpdateGroupUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class SaveGroupViewModel @Inject constructor(
    private val getGroupUseCase: GetGroupUseCase,
    private val addGroupUseCase: AddGroupUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase,
    private val errorEvent: SingleLiveEvent<String>
) :
    ViewModel() {

    var gid = -1
    val name = MutableLiveData("")
    val info = MutableLiveData("")
    val image = MutableLiveData("")
    val isShare = MutableLiveData(false)

    val completeEvent = SingleLiveEvent<Unit>()

    private var isUpdateGroup = false

    fun loadGroup() {
        viewModelScope.launch {
            val group = getGroupUseCase(gid)

            when (group) {
                is Result.Success -> completeEvent.value = Unit
                is Result.Failure -> errorEvent.value =
                    "code: ${group.code} message: ${group.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(group.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    fun saveGroup() {
        viewModelScope.launch {
            val result = if (isUpdateGroup) {
                updateGroup()
            } else {
                addNewGroup()
            }
            when (result) {
                is Result.Success -> completeEvent.value = Unit
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

    private suspend fun updateGroup(): Result<Void> = updateGroupUseCase(
        Group(gid, name.safeValue, info.safeValue, image.safeValue, isShare.safeValue)
    )

    private suspend fun addNewGroup(): Result<Void> = addGroupUseCase(
        Group(
            Random.nextInt(Int.MAX_VALUE),
            name.safeValue,
            info.safeValue,
            image.safeValue,
            isShare.safeValue
        )
    )

    fun setImage(dataString: String?) {
        image.value = dataString
    }
}