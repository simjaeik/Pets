package com.lacuc.pets.ui.animal.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.Openable
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseAnimalBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChooseAnimalFragment : DaggerFragment() {
    private var _binding: FragmentChooseAnimalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ChooseAnimalViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseAnimalBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        setupDrawer()

        binding.recyclerViewChooseAnimal.apply {
            adapter = ChooseAnimalAdapter()
            layoutManager = object : LinearLayoutManager(context) {
                override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
                    RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            }
        }

        viewModel.loadGroups()

        viewModel.clickItem.observe(viewLifecycleOwner) {
            val action =
                ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToAnimalDetailFragment(it.animal)
            navController.navigate(action)
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.chooseAnimalFragment), binding.root as Openable)
        binding.toolbarChooseAnimal.setupWithNavController(navController, appBarConfiguration)

        binding.toolbarChooseAnimal.inflateMenu(R.menu.menu_group)

        binding.toolbarChooseAnimal.setOnMenuItemClickListener {
            val action =
                ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToAddAnimalFragment(null)
            navController.navigate(action)
            true
        }
    }

    private fun setupDrawer() {
        binding.drawerChooseAnimal.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}