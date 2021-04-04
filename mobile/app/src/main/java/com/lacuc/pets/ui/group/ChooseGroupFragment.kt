package com.lacuc.pets.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentChooseGroupBinding
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class ChooseGroupFragment : DaggerFragment() {
    private var _binding: FragmentChooseGroupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ChooseGroupViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGroupBinding.inflate(inflater, container, false).apply {
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar()

        viewModel.clickItem.observe(viewLifecycleOwner) {
            Toast.makeText(context, "clicked: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.chooseGroupFragment))
        binding.toolbarChooseGroup.setupWithNavController(navController, appBarConfiguration)
        binding.toolbarChooseGroup.inflateMenu(R.menu.menu_group)
        binding.toolbarChooseGroup.setOnMenuItemClickListener {
            Timber.d("onOptionsItemSelected")
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}