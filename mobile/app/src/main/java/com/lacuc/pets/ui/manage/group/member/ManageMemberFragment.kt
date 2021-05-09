package com.lacuc.pets.ui.manage.group.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentManageMemberBinding
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

    private val navController: NavController by lazy { findNavController() }

    private val args: ManageMemberFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMembers(args.gid)
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

        binding.recyclerViewManageMember.setup(ManageMemberAdapter())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}