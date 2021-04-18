package com.lacuc.pets.data.group

import com.lacuc.pets.data.group.entity.Group

interface GroupRepository {
    fun loadGroup(email: String): List<Group>

    fun saveGroup(email: String, group: Group)
}