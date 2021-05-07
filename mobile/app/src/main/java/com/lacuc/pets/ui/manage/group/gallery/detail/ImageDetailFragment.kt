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

    private val disposables = CompositeDisposable()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setImageTabListener()

        addImageTags()

        binding.btnImageDetailUp.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun setImageTabListener() {
        disposables.add(
            binding.ivImageDetailImage.tabEvent.subscribe {
                val visibility = if (binding.btnImageDetailUp.visibility == View.VISIBLE)
                    View.INVISIBLE
                else View.VISIBLE

                binding.btnImageDetailUp.visibility = visibility
                binding.chipGroupImageDetail.visibility = visibility
            }
        )
    }

    private fun addImageTags() {
        viewModel.image.observe(viewLifecycleOwner) {
            val tag = createTagChip(it.tag)
            binding.chipGroupImageDetail.addView(tag)
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