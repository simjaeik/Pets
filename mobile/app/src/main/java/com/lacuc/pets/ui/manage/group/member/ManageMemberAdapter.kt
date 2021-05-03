package com.lacuc.pets.ui.manage.group.member

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder
import java.lang.reflect.Member

class ManageMemberAdapter : RecyclerView.Adapter<ViewBindingHolder<*>>(),
    BindableAdapter<List<Member>> {

    private val items = ArrayList<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingHolder<*> {
        return ViewBindingHolder<ViewDataBinding>(parent.context, viewType)
    }

    override fun onBindViewHolder(holder: ViewBindingHolder<*>, position: Int) {
        if (position in 1..items.size)
            holder.binding.setVariable(BR.item, items[position - 1])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size + 2

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> R.layout.item_member_header
        items.size + 1 -> R.layout.item_member_footer
        else -> R.layout.item_member
    }

    override fun setData(data: List<Member>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}