package com.lacuc.pets.ui.manage.animal.detail.medical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAddMedicalBinding
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddMedicalFragment : DaggerFragment() {
    private var _binding: FragmentAddMedicalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AddMedicalViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMedicalBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarAddMedical.setupWithNavController(navController)

        setOnCompleteEventObserver()
    }

    private fun setOnCompleteEventObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.previousBackStackEntry?.savedStateHandle?.set("onCompleteEvent", 1)
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}