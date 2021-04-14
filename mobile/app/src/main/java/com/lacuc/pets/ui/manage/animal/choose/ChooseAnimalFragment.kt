package com.lacuc.pets.ui.manage.animal.choose

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
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseAnimalBinding
import com.lacuc.pets.util.setup
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
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupDrawer()

        binding.recyclerViewChooseAnimal.setup(ChooseAnimalAdapter())

        setOnClickEventObserver()

        setOnCompleteObserver()
    }

    private fun setOnCompleteObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("onCompleteEvent")
            ?.observe(viewLifecycleOwner) {
                viewModel.loadGroups()
            }
    }

    private fun setOnClickEventObserver() {
        viewModel.animalClickEvent.observe(viewLifecycleOwner) {
            val action = ChooseAnimalFragmentDirections
                .actionChooseAnimalFragmentToAnimalDetailFragment(it.animal)
            navController.navigate(action)
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.chooseAnimalFragment), binding.root as Openable
        )
        binding.toolbarChooseAnimal.apply {
            setupWithNavController(navController, appBarConfiguration)

            inflateMenu(R.menu.menu_group)
            setOnMenuItemClickListener {
                val action = ChooseAnimalFragmentDirections
                    .actionChooseAnimalFragmentToAddAnimalFragment(null)
                navController.navigate(action)
                true
            }
        }
    }

    private fun setupDrawer() {
        binding.navDrawerChooseAnimal.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}