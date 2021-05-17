package com.lacuc.pets.ui.manage.group.member

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.data.group.entity.Member
import com.lacuc.pets.databinding.ItemMemberBinding
import com.lacuc.pets.databinding.ItemMemberFooterBinding
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.ViewBindingHolder

class ManageMemberAdapter(val onChange: (Member, String) -> Unit) :
    RecyclerView.Adapter<ViewBindingHolder<*>>(),
    BindableAdapter<List<Member>> {

    private val items = ArrayList<Member>()

    val inviteMemberEvent = SingleLiveEvent<Unit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingHolder<*> =
        ViewBindingHolder<ViewDataBinding>(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<*>, position: Int) {
        if (position in 1..items.size)
            holder.binding.setVariable(BR.item, items[position - 1])
        when (holder.binding) {
            is ItemMemberBinding -> {
                holder.binding.dropdownItemMemberAuthority
                    .setOnItemClickListener { parent, _, pos, _ ->
                        onChange(items[position - 1], parent.getItemAtPosition(pos) as String)
                    }
            }
            is ItemMemberFooterBinding -> {
                holder.binding.btnItemMemberFooterInviteMember.setOnClickListener {
                    inviteMemberEvent.value = Unit
                }
            }
        }
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