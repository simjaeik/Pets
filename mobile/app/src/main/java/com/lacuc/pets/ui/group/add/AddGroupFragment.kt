package com.lacuc.pets.ui.group.add

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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentAddGroupBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddGroupFragment : DaggerFragment() {

    private var _binding: FragmentAddGroupBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController by lazy { findNavController() }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AddGroupViewModel by viewModels { viewModelFactory }

    private val requestActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { intent ->
                viewModel.setImage(intent.dataString)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddGroupBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.groupUpdated.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }

        setupToolbar()

        binding.btnAddGroupPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            requestActivity.launch(intent)
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarAddGroup.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}