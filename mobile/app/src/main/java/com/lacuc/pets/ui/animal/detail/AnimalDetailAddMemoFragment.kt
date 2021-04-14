package com.lacuc.pets.ui.animal.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAddMemoBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AnimalDetailAddMemoFragment : DaggerFragment() {

    private var _binding: FragmentAddMemoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AnimalDetailAddMemoViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarAddMemo.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}