package com.lacuc.pets.ui.group

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.domain.group.GroupItem

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<GroupItem>) {
    if (listView.adapter == null)
        listView.adapter = ChooseGroupAdapter()
    listView.layoutManager = object : LinearLayoutManager(listView.context) {
        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
            RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
    }
    (listView.adapter as ChooseGroupAdapter).addList(items)
}