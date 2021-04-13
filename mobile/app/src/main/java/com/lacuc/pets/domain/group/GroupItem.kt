package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.Group

class GroupItem(group: Group, val clickListener: (GroupItem) -> Unit) {
    val name = group.name
    val info = group.info
    val image = group.image
}