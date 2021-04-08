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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseGroupBinding
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
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        binding.recyclerViewChooseGroup.apply {
            adapter = ChooseGroupAdapter()
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
            val action = ChooseGroupFragmentDirections
                .actionChooseGroupFragmentToChooseAnimalFragment(it.getName())
            navController.navigate(action)
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.chooseGroupFragment))
        binding.toolbarChooseGroup.setupWithNavController(navController, appBarConfiguration)

        binding.toolbarChooseGroup.inflateMenu(R.menu.menu_group)

        binding.toolbarChooseGroup.setOnMenuItemClickListener {
            val action =
                ChooseGroupFragmentDirections.actionChooseGroupFragmentToAddGroupFragment("그룹 생성")
            navController.navigate(action)
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}