package com.lacuc.pets.util

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ViewBindingHolder<VDB : ViewDataBinding>(val binding: VDB) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(context: Context, @LayoutRes layoutId: Int) : this(
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId,
            null,
            false
        )
    )

}