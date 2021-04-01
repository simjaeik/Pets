package com.lacuc.pets.data

interface GroupRepository {
    fun loadGroup(): List<Group>
}