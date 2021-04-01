package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Group

class GroupItem(val group: Group, val clickListener: (GroupItem) -> Unit) {
}