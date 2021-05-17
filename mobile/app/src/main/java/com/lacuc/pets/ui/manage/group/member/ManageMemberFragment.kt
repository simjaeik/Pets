package com.lacuc.pets.ui.manage.group.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentManageMemberBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setup
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ManageMemberFragment : DaggerFragment() {

    private var _binding: FragmentManageMemberBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ManageMemberViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let {
            viewModel.gid = it
            viewModel.loadMembers()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageMemberBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbarManageMember.setupWithNavController(navController)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ManageMemberAdapter()
        binding.recyclerViewManageMember.setup(adapter)

        setupInviteMemberBtnObserver(adapter)
    }

    private fun setupInviteMemberBtnObserver(adapter: ManageMemberAdapter) {
        adapter.inviteMemberEvent.observe(viewLifecycleOwner) {
            val action = ManageMemberFragmentDirections
                .actionManageMemberFragmentToInviteMemberFragment()
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}