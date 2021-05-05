package com.lacuc.pets.ui.manage.animal.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.Openable
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.DrawerHeaderChooseAnimalBinding
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

    private val args: ChooseAnimalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseAnimalBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        setupDrawerHeader(inflater)

        return binding.root
    }

    private fun setupDrawerHeader(inflater: LayoutInflater) {
        val navHeaderBinding =
            DrawerHeaderChooseAnimalBinding.inflate(inflater, binding.navDrawerChooseAnimal, false)
        navHeaderBinding.item = args.group

        binding.navDrawerChooseAnimal.addHeaderView(navHeaderBinding.root)
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

            inflateMenu(R.menu.menu_add)
            setOnMenuItemClickListener {
                val action = ChooseAnimalFragmentDirections
                    .actionChooseAnimalFragmentToAddAnimalFragment(null)
                navController.navigate(action)
                true
            }

            title = args.group.name
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
                .actionChooseAnimalFragmentToSaveGroupFragment("그룹 정보 수정", args.group)
        R.id.manageMemberFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToManageMemberFragment(args.group.gid)
        R.id.galleryFragment ->
            ChooseAnimalFragmentDirections.actionChooseAnimalFragmentToGalleryFragment(args.group.gid)
        else -> null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}