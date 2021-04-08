package com.lacuc.pets.data.group

interface GroupRepository {
    fun loadGroup(email: String): List<Group>

    fun saveGroup(email: String, group: Group)
}