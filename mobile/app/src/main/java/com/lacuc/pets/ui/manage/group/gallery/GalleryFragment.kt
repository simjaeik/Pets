package com.lacuc.pets.ui.manage.group.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentGalleryBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GalleryFragment : DaggerFragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: GalleryViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let {
            viewModel.gid = it
            viewModel.loadImages()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()

        binding.recyclerviewGallery.apply {
            adapter = GalleryAdapter()
            layoutManager = GridLayoutManager(view.context, 3)
        }

        setOnCompleteObserver()

        viewModel.imageClickEvent.observe(viewLifecycleOwner) {
            val action = GalleryFragmentDirections
                .actionGalleryFragmentToImageDetailFragment()
            activityViewModel.iid = it.image.IID
            navController.navigate(action)
        }
    }

    private fun setOnCompleteObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("onCompleteEvent")
            ?.observe(viewLifecycleOwner) {
                viewModel.loadImages()
            }
    }

    private fun setupToolbar() {
        binding.toolbarGallery.apply {
            setupWithNavController(navController)

            inflateMenu(R.menu.menu_add)
            setOnMenuItemClickListener {
                val action = GalleryFragmentDirections
                    .actionGalleryFragmentToSaveImageFragment()
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