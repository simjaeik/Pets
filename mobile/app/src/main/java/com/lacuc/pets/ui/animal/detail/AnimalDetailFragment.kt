package com.lacuc.pets.ui.animal.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAnimalDetailBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AnimalDetailFragment : DaggerFragment() {

    private var _binding: FragmentAnimalDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AnimalDetailViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}