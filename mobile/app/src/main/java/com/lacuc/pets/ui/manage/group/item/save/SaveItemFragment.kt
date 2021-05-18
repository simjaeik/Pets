package com.lacuc.pets.ui.manage.group.item.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSaveItemBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SaveItemFragment : DaggerFragment() {
    private var _binding: FragmentSaveItemBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SaveItemViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.hid?.let {
            viewModel.hid = it
            viewModel.loadItem()
        }
        activityViewModel.gid?.let { viewModel.gid = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveItemBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbarSaveItem.setupWithNavController(navController)

        setOnCompleteObserver()
    }

    private fun setOnCompleteObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.previousBackStackEntry?.savedStateHandle?.set("onCompleteEvent", true)
            navController.navigateUp()
        }
    }
}