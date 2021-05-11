package com.lacuc.pets.ui.manage.animal.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAnimalDetailBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.doOnTabSelectedListener
import com.lacuc.pets.util.setup
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AnimalDetailFragment : DaggerFragment() {

    private var _binding: FragmentAnimalDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AnimalDetailViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.aid?. let { viewModel.aid = it }
        viewModel.loadDetailItem(0)
        viewModel.loadAnimal()
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarAnimalDetail.setupWithNavController(navController)

        binding.recyclerviewAnimalDetail.setup(AnimalDetailAdapter())

        setOnCompleteObserver()

        binding.tabLayoutAnimalDetail.doOnTabSelectedListener {
            viewModel.loadDetailItem(it.position)
            setFabClickListener(it.position)
            saveCurrentTabPosition(it)
        }

        viewModel.animal.observe(viewLifecycleOwner) {
            binding.item = it
        }

        val currentTabPosition = restorePreviousTabPosition()
        binding.tabLayoutAnimalDetail.getTabAt(currentTabPosition)?.select()
    }

    private fun setOnCompleteObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>("onCompleteEvent")
            ?.observe(viewLifecycleOwner) {
                viewModel.loadDetailItem(it)
            }
    }

    private fun restorePreviousTabPosition() = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<Int>("TabPosition")
        ?: let {
            binding.fabAnimalDetail.setOnClickListener { navToEditAnimal() }
            0
        }

    private fun saveCurrentTabPosition(it: TabLayout.Tab) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set("TabPosition", it.position)
    }

    private fun setFabClickListener(position: Int = 0) {
        when (position) {
            0 -> {
                fabIconChange(R.drawable.ic_baseline_edit_24)
                binding.fabAnimalDetail.setOnClickListener { navToEditAnimal() }
            }
            1 -> {
                fabIconChange(R.drawable.ic_baseline_add_24)
                binding.fabAnimalDetail.setOnClickListener { navToAddMedical() }
            }
            2 -> {
                fabIconChange(R.drawable.ic_baseline_add_24)
                binding.fabAnimalDetail.setOnClickListener { navToAddMemo() }
            }
        }
    }

    private fun fabIconChange(@DrawableRes id: Int) {
        binding.fabAnimalDetail.apply {
            hide()
            setImageResource(id)
            show()
        }
    }

    private fun navToEditAnimal() {
        val action = AnimalDetailFragmentDirections
            .actionAnimalDetailFragmentToAddAnimalFragment()
        navController.navigate(action)
    }

    private fun navToAddMedical() {
        val action = AnimalDetailFragmentDirections
            .actionAnimalDetailFragmentToAddMedicalFragment()
        navController.navigate(action)
    }

    private fun navToAddMemo() {
        val action = AnimalDetailFragmentDirections
            .actionAnimalDetailFragmentToAddMemoFragment()
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        activityViewModel.aid = null
    }

}