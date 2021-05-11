package com.lacuc.pets.ui.manage.animal.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.Openable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.DrawerHeaderChooseAnimalBinding
import com.lacuc.pets.databinding.FragmentChooseAnimalBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChooseAnimalFragment : DaggerFragment() {

    private var _binding: FragmentChooseAnimalBinding? = null
    private val binding get() = _binding!!

    private var _headerBinding: DrawerHeaderChooseAnimalBinding? = null
    private val headerBinding get() = _headerBinding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ChooseAnimalViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let {
            viewModel.gid = it
            viewModel.loadGroup()
            viewModel.loadAnimals()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseAnimalBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        _headerBinding =
            DrawerHeaderChooseAnimalBinding.inflate(inflater, binding.navDrawerChooseAnimal, false)
        binding.navDrawerChooseAnimal.addHeaderView(headerBinding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupDrawer()

        binding.recyclerViewChooseAnimal.setup(ChooseAnimalAdapter())

        setOnClickEventObserver()

        setOnCompleteObserver()

        setupGroupObserver()
    }

    private fun setupGroupObserver() {
        viewModel.group.observe(viewLifecycleOwner) {
            headerBinding.item = it
            headerBinding.notifyChange()
            binding.toolbarChooseAnimal.title = it.name
        }
    }

    private fun setOnCompleteObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("onCompleteEvent")
            ?.observe(viewLifecycleOwner) {
                viewModel.loadAnimals()
            }
    }

    private fun setOnClickEventObserver() {
        viewModel.animalClickEvent.observe(viewLifecycleOwner) {
            val action = ChooseAnimalFragmentDirections
                .actionChooseAnimalFragmentToAnimalDetailFragment()
            activityViewModel.aid = it.aid
            navController.navigate(action)
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.chooseAnimalFragment), binding.root as Openable
        )
        binding.toolbarChooseAnimal.apply {
            setupWithNavController(navController, appBarConfiguration)

            inflateMenu(R.menu.menu_add)
            setOnMenuItemClickListener {
                val action = ChooseAnimalFragmentDirections
                    .actionChooseAnimalFragmentToAddAnimalFragment()
                navController.navigate(action)
                true
            }
        }
    }

    private fun setupDrawer() {
        binding.navDrawerChooseAnimal.setNavigationItemSelectedListener { menu ->
            val action = getActionByMenuId(menu.itemId)
            action?.let {
                navController.navigate(action)
                true
            } ?: false
        }
    }

    private fun getActionByMenuId(menuId: Int) = when (menuId) {
        R.id.userProfileFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToUserProfileFragment()
        R.id.saveGroupFragment ->
            ChooseAnimalFragmentDirections
                .actionChooseAnimalFragmentToSaveGroupFragment("그룹 정보 수정")
        R.id.manageMemberFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToManageMemberFragment()
        R.id.galleryFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToGalleryFragment()
        R.id.itemListFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToItemListFragment()
        else -> null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _headerBinding = null
    }
}