package com.lacuc.pets.ui.group.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseGroupBinding
import com.lacuc.pets.util.setup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChooseGroupFragment : DaggerFragment() {
    private var _binding: FragmentChooseGroupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ChooseGroupViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGroupBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        binding.recyclerViewChooseGroup.setup(ChooseGroupAdapter())

        setGroupClickEventObserver()

        setOnCompleteObserver()
    }

    private fun setGroupClickEventObserver() {
        viewModel.groupClickEvent.observe(viewLifecycleOwner) {
            val action = ChooseGroupFragmentDirections
                .actionChooseGroupFragmentToChooseAnimalFragment(it.name)
            navController.navigate(action)
        }
    }

    private fun setOnCompleteObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("onCompleteEvent")
            ?.observe(viewLifecycleOwner) {
                viewModel.loadGroups()
            }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.chooseGroupFragment))
        binding.toolbarChooseGroup.apply {
            setupWithNavController(navController, appBarConfiguration)

            inflateMenu(R.menu.menu_group)
            setOnMenuItemClickListener {
                val action = ChooseGroupFragmentDirections
                    .actionChooseGroupFragmentToAddGroupFragment("그룹 생성")
                navController.navigate(action)
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}