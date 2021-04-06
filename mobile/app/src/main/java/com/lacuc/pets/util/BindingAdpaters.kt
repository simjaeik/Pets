package com.lacuc.pets.util

import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.ui.group.choose.ChooseGroupAdapter

@BindingAdapter("app:image")
fun setImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

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