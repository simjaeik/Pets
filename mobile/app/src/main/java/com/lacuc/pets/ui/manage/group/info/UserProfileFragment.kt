package com.lacuc.pets.ui.manage.group.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentUserProfileBinding
import com.lacuc.pets.ui.manage.ManageViewModel
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class UserProfileFragment : DaggerFragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UserProfileViewModel by viewModels { viewModelFactory }

    private val activityViewModel: ManageViewModel by activityViewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarUserProfile.setupWithNavController(navController)

        setCompleteBtnEnable()

        setCompleteEventObserver()
    }

    private fun setCompleteEventObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    private fun setCompleteBtnEnable() {
        disposables.add(
            viewModel.bindCompleteBtnEnable(
                binding.textInputUserProfileName.textChanges(),
                binding.textInputUserProfileEmail.textChanges(),
                binding.textInputUserProfileNickName.textChanges()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposables.dispose()
    }
}