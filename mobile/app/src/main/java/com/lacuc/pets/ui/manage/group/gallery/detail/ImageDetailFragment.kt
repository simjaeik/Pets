package com.lacuc.pets.ui.manage.group.gallery.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentImageDetailBinding
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ImageDetailFragment : DaggerFragment() {

    private var _binding: FragmentImageDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ImageDetailViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val args: ImageDetailFragmentArgs by navArgs()

    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setImage(args.image)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        disposables = CompositeDisposable()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()

        setImageTabListener()

        addImageTags()

        setOnUpdateEventObserver()
    }

    private fun setOnUpdateEventObserver() {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>("onUpdateEvent")
            ?.observe(viewLifecycleOwner) { iid ->
                viewModel.loadImage(args.gid, iid)
            }
        navController.previousBackStackEntry?.savedStateHandle?.set("onCompleteEvent", true)
    }

    private fun setupToolbar() {
        binding.toolbarImageDetail.apply {
            setupWithNavController(navController)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_edit -> {
                        navigateToSaveImageFragment()
                        return@setOnMenuItemClickListener true
                    }
                }
                false
            }
        }

    }

    private fun navigateToSaveImageFragment() {
        val action = ImageDetailFragmentDirections
            .actionImageDetailFragmentToSaveImageFragment(args.gid, viewModel.image.value)
        navController.navigate(action)
    }

    private fun setImageTabListener() {
        disposables.add(
            binding.ivImageDetailImage.tabEvent.subscribe {
                val visibility =
                    if (binding.toolbarImageDetail.visibility == View.VISIBLE)
                        View.INVISIBLE
                    else View.VISIBLE

                binding.toolbarImageDetail.visibility = visibility
                binding.chipGroupImageDetail.visibility = visibility
            }
        )
    }

    private fun addImageTags() {
        viewModel.image.observe(viewLifecycleOwner) {
            binding.chipGroupImageDetail.removeAllViews()
            val tagList = it.tag.split(",")
            for (tag in tagList) {
                val chip = createTagChip(tag)
                binding.chipGroupImageDetail.addView(chip)
            }
        }
    }

    private fun createTagChip(text: String): Chip = Chip(context).apply {
        setChipBackgroundColorResource(R.color.primary_50)
        setCloseIconTintResource(R.color.primary_300)
        this.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposables.dispose()
    }
}