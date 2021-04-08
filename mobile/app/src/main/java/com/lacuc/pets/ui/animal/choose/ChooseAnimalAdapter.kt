package com.lacuc.pets.ui.animal.choose

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.databinding.ItemAnimalBinding
import com.lacuc.pets.domain.animal.AnimalItem
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder

class ChooseAnimalAdapter : RecyclerView.Adapter<ViewBindingHolder<ItemAnimalBinding>>(),
    BindableAdapter<List<AnimalItem>> {

    private val items = ArrayList<AnimalItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingHolder<ItemAnimalBinding> = ViewBindingHolder(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<ItemAnimalBinding>, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = R.layout.item_animal

    override fun setData(data: List<AnimalItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}