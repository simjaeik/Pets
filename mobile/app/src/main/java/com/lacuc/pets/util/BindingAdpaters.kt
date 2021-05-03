package com.lacuc.pets.util

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("items")
@Suppress("UNCHECKED_CAST")
fun <T> setItems(listView: RecyclerView, items: T) {
    if (items != null && listView.adapter is BindableAdapter<*>) {
        (listView.adapter as BindableAdapter<T>).setData(items)
    }
}

@BindingAdapter("arrayId", "layoutId")
fun setArrayAdapter(textView: AutoCompleteTextView, arrayId: Int, layoutId: Int) {
    with(textView.context) {
        val items = resources.getStringArray(arrayId)
        textView.setAdapter(ArrayAdapter(this, layoutId, items))
    }
}