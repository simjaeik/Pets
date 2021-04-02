package com.lacuc.pets.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseGroupBinding

class ChooseGroupFragment : Fragment() {
    private var _binding: FragmentChooseGroupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChooseGroupViewModel by viewModels { ViewModelFactory() }

    lateinit var adapter: ChooseGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        adapter = ChooseGroupAdapter()

        binding.recyclerViewChooseGroup.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ChooseGroupFragment.adapter
        }

        viewModel.groupItems.observe(viewLifecycleOwner) {
            adapter.addList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}