package com.lacuc.pets.domain.item

import com.lacuc.pets.data.group.entity.ItemHistory

class ItemHistoryItem(val itemHistory: ItemHistory, val clickListener: (ItemHistoryItem) -> Unit) {
    val name = itemHistory.name
    val category = itemHistory.category
    val link = itemHistory.link
    val price = itemHistory.price
}