package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group

interface GroupDataSource {
    suspend fun loadGroup(): Result<List<Group>>

    fun saveGroup(email: String, group: Group)
}