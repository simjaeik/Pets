package com.lacuc.pets.ui.manage.group.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentItemListBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setup
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ItemListFragment : DaggerFragment() {
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ItemListViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let {
            viewModel.gid = it
            viewModel.loadItems()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        activityViewModel.hid = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()

        binding.recyclerviewItemList.setup(ItemListAdapter())

        viewModel.itemClickEvent.observe(viewLifecycleOwner) {
            activityViewModel.hid = it.itemHistory.hid
            val action = ItemListFragmentDirections.actionItemListFragmentToSaveItemFragment()
            navController.navigate(action)
        }
    }

    private fun setupToolbar() {
        binding.toolbarItemList.apply {
            setupWithNavController(navController)

            inflateMenu(R.menu.menu_add)
            setOnMenuItemClickListener {
                val action = ItemListFragmentDirections.actionItemListFragmentToSaveItemFragment()
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