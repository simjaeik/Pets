package com.lacuc.pets.ui.manage.group.choose

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.databinding.ItemGroupBinding
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder

class ChooseGroupAdapter : RecyclerView.Adapter<ViewBindingHolder<ItemGroupBinding>>(),
    BindableAdapter<List<GroupItem>> {

    private val items = ArrayList<GroupItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingHolder<ItemGroupBinding> =
        ViewBindingHolder(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<ItemGroupBinding>, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = R.layout.item_group

    override fun setData(data: List<GroupItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}