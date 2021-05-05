package com.lacuc.pets.ui.manage.group.gallery.save

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSaveImageBinding
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SaveImageFragment : DaggerFragment() {

    private var _binding: FragmentSaveImageBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SaveImageViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val requestImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { intent -> viewModel.setImage(intent.dataString) }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveImageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSaveImage.setupWithNavController(navController)

        binding.btnSaveImagePickImage.setOnClickListener {
            requestImage()
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