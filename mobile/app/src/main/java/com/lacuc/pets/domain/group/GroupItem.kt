package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.Group

class GroupItem(val group: Group, val clickListener: (GroupItem) -> Unit) {

    fun getName() = group.name

    fun getInfo() = group.info

    fun getImage() = group.image
}