package com.lacuc.pets.ui.group

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.domain.group.GroupItem

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<GroupItem>) {
    if (listView.adapter == null)
        listView.adapter = ChooseGroupAdapter()
    (listView.adapter as ChooseGroupAdapter).addList(items)
}