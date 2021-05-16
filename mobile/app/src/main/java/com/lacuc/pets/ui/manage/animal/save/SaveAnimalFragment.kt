package com.lacuc.pets.ui.manage.animal.save

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSaveAnimalBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SaveAnimalFragment : DaggerFragment() {

    private var _binding: FragmentSaveAnimalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SaveAnimalViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val requestImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { intent -> viewModel.setImage(intent.dataString) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let { viewModel.gid = it }
        activityViewModel.aid?.let {
            viewModel.aid = it
            viewModel.loadAnimal()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveAnimalBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSaveAnimal.setupWithNavController(navController)

        binding.btnSaveAnimalPickImage.setOnClickListener {
            requestImage()
        }

        setOnCompleteEventObserver()

        setOnUpdateEventObserver()
    }

    private fun setOnCompleteEventObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.previousBackStackEntry?.savedStateHandle?.set("onCompleteEvent", true)
            navController.navigateUp()
        }
    }

    private fun setOnUpdateEventObserver() {
        viewModel.updateEvent.observe(viewLifecycleOwner) {
            navController.previousBackStackEntry?.savedStateHandle?.set("onUpdateEvent", true)
            navController.navigateUp()
        }
    }

    private fun requestImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        requestImageLauncher.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}