package com.lacuc.pets.ui.manage.group.item

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.data.group.entity.ItemHistory
import com.lacuc.pets.databinding.ItemItemBinding
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder

class ItemListAdapter : RecyclerView.Adapter<ViewBindingHolder<ItemItemBinding>>(),
    BindableAdapter<List<ItemHistory>> {

    private val items = arrayListOf<ItemHistory>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingHolder<ItemItemBinding> = ViewBindingHolder(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<ItemItemBinding>, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<ItemHistory>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_item
}