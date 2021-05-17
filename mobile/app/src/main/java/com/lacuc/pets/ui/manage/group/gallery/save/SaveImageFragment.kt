package com.lacuc.pets.ui.manage.group.gallery.save

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSaveImageBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class SaveImageFragment : DaggerFragment() {

    private var _binding: FragmentSaveImageBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SaveImageViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val requestImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { intent ->
                val path = obtainFilePath(intent.data)
                Timber.d(path)
                viewModel.setImage(path)
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                requestImageLauncher.launch(intent)
            }
        }

    private fun obtainFilePath(contentUri: Uri?): String? {
        return contentUri?.let {
            val cursor = requireActivity().contentResolver
                .query(contentUri, null, null, null, null)
            val path = cursor?.let {
                cursor.moveToNext()
                cursor.getString(cursor.getColumnIndex("_data"))
            }
            cursor?.close()

            path
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.gid?.let { viewModel.gid = it }
        activityViewModel.iid?.let {
            viewModel.iid = it
            viewModel.loadImage()
        }
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

        setupTagChips()

        setOnCompleteEventObserver()

        setOnUpdateEventObserver()

        setOnLoadImageObserver()
    }

    private fun setOnLoadImageObserver() {
        viewModel.loadImageEvent.observe(viewLifecycleOwner) {
            for (tag in viewModel.addedTagList) {
                addChip(tag)
            }
        }
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

    private fun setupTagChips() {
        binding.textInputSaveImageTag.apply {
            setupTagAdapter()

            setOnItemClickListener { _, view, _, _ ->
                val text = (view as? TextView)?.text?.toString() ?: ""
                if (viewModel.addTag(text)) {
                    addChip(text)
                }
            }
        }
    }

    private fun addChip(text: String) {
        val chip = createTagChip(text)
        binding.chipGroupSaveImage.addView(chip)
    }

    private fun createTagChip(text: String): Chip = Chip(context).apply {
        setChipBackgroundColorResource(R.color.primary_50)
        setCloseIconTintResource(R.color.primary_300)
        this.text = text
        isCloseIconVisible = true
        setOnCloseIconClickListener { tag ->
            if (viewModel.removeTag((tag as Chip).text.toString()))
                binding.chipGroupSaveImage.removeView(tag)
        }
    }

    private fun AutoCompleteTextView.setupTagAdapter() {
        // 텍스트가 입력될 때 마다, 기존의 태그 목록에서 사용자가
        // 입력중인 태그를 상단에 보여주기 위해 어댑터를 재등록
        doOnTextChanged { text, _, _, _ ->
            setAdapter(
                ArrayAdapter(
                    context,
                    R.layout.item_menu_dropdown,
                    (setOf(text.toString()) + viewModel.tagSet).toList()
                )
            )
        }
    }

    private fun requestImage() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                requestImageLauncher.launch(intent)
            }
            else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}