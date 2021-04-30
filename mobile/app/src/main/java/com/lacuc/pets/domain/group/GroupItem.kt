package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.entity.Group

class GroupItem(val group: Group, val clickListener: (GroupItem) -> Unit) {
    val name = group.name
    val info = group.info
    val image = group.image
}