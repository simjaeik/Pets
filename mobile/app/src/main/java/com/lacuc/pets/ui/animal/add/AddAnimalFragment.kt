package com.lacuc.pets.ui.animal.add

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
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAddAnimalBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddAnimalFragment : DaggerFragment() {
    private var _binding: FragmentAddAnimalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AddAnimalViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val requestActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { intent ->
                viewModel.setImage(intent.dataString)
            }
        }

    private val args: AddAnimalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAnimalBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        if (savedInstanceState == null) {
            args.animal?.let {
                viewModel.initData(it)
            }
        }

        binding.btnAddAnimalPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            requestActivity.launch(intent)
        }

        viewModel.animalUpdated.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarAddAnimal.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}