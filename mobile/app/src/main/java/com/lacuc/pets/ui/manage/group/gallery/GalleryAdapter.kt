package com.lacuc.pets.ui.manage.group.gallery

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder

class GalleryAdapter : RecyclerView.Adapter<ViewBindingHolder<*>>(),
    BindableAdapter<List<GroupItem>> {

    private val items = ArrayList<GroupItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingHolder<*> =
        ViewBindingHolder<ViewDataBinding>(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<*>, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<GroupItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_group_image
}