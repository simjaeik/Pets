package com.lacuc.pets.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseGroupBinding

class ChooseGroupFragment : Fragment() {
    private var _binding: FragmentChooseGroupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChooseGroupViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}