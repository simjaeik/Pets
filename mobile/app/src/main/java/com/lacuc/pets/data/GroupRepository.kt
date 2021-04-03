package com.lacuc.pets.data

interface GroupRepository {
    fun loadGroup(email: String): List<Group>
}