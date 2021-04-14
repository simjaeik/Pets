package com.lacuc.pets.ui.manage.animal.detail

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.BR
import com.lacuc.pets.R
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailMedicalItem
import com.lacuc.pets.domain.animal.AnimalDetailMemoItem
import com.lacuc.pets.util.BindableAdapter
import com.lacuc.pets.util.ViewBindingHolder

class AnimalDetailAdapter : RecyclerView.Adapter<ViewBindingHolder<*>>(),
    BindableAdapter<List<AnimalDetailItem>> {

    private val items = ArrayList<AnimalDetailItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewBindingHolder<ViewDataBinding>(parent.context, viewType)

    override fun onBindViewHolder(holder: ViewBindingHolder<*>, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is AnimalDetailDetailItem -> R.layout.item_animal_detail_detail
        is AnimalDetailMedicalItem -> R.layout.item_animal_detail_medical
        is AnimalDetailMemoItem -> R.layout.item_animal_detail_memo
    }

    override fun setData(data: List<AnimalDetailItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}