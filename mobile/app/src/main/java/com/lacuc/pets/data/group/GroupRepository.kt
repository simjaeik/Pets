package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group

interface GroupRepository {
    suspend fun loadGroup(): Result<List<Group>>

    suspend fun saveGroup(group: Group)
}