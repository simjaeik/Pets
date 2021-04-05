package com.lacuc.pets.data.group

interface GroupDataSource {
    fun loadGroup(email: String): List<Group>
}