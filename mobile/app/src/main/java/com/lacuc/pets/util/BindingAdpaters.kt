package com.lacuc.pets.util

import android.text.util.Linkify
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.util.Consumer
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import java.util.regex.Pattern

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
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

@BindingAdapter("link")
fun setLink(textView: TextView, link: String) {
    val mTransform = Linkify.TransformFilter { _, _ -> link }
    val pattern = Pattern.compile(textView.text.toString())

    Linkify.addLinks(textView, pattern, null, null, mTransform)
}

@BindingAdapter("test")
fun setTest(tabLayout: TabLayout, listener: Consumer<Int>) {
    tabLayout.doOnTabSelectedListener { listener.accept(tabLayout.selectedTabPosition) }
}

@BindingAdapter("date")
fun setDate(textView: TextView, date: String) {
    textView.text = date.split("T")[0]
}