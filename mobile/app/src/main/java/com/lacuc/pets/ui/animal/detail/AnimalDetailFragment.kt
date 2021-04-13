package com.lacuc.pets.ui.animal.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAnimalDetailBinding
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AnimalDetailFragment : DaggerFragment() {

    private var _binding: FragmentAnimalDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AnimalDetailViewModel by viewModels { viewModelFactory }

    private val args: AnimalDetailFragmentArgs by navArgs()

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            item = args.animal
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        binding.recyclerviewAnimalDetail.apply {
            adapter = AnimalDetailAdapter()
            layoutManager = object : LinearLayoutManager(context) {
                override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
                    RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            }
        }

        viewModel.refresh()

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.switchItem(tab.position)
                setFabClickListener(tab.position)
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "TabPosition",
                    tab.position
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        setFabClickListener()

        viewModel.initItem(AnimalDetailDetailItem(args.animal))

        val currentTabPosition =
            navController.currentBackStackEntry?.savedStateHandle?.get<Int>("TabPosition") ?: 0
        binding.tabs.getTabAt(currentTabPosition)?.select()
    }

    private fun setFabClickListener(position: Int = 0) {
        when (position) {
            0 -> {
                binding.fabDetailAdd.hide()
                binding.fabDetailAdd.setImageResource(R.drawable.ic_baseline_edit_24)
                binding.fabDetailAdd.show()
                binding.fabDetailAdd.setOnClickListener {
                    val action = AnimalDetailFragmentDirections
                        .actionAnimalDetailFragmentToAddAnimalFragment(args.animal)
                    navController.navigate(action)
                }
            }
            1 -> {
                binding.fabDetailAdd.hide()
                binding.fabDetailAdd.setImageResource(R.drawable.ic_baseline_add_24)
                binding.fabDetailAdd.show()
                binding.fabDetailAdd.setOnClickListener {
                    val action = AnimalDetailFragmentDirections
                        .actionAnimalDetailFragmentToAnimalDetailAddMedicalFragment()
                    navController.navigate(action)
                }
            }
            2 -> {
                binding.fabDetailAdd.hide()
                binding.fabDetailAdd.setImageResource(R.drawable.ic_baseline_add_24)
                binding.fabDetailAdd.show()
                binding.fabDetailAdd.setOnClickListener {
                    val action = AnimalDetailFragmentDirections
                        .actionAnimalDetailFragmentToAnimalDetailAddMemoFragment()
                    navController.navigate(action)
                }
            }
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarAnimalDetail.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}