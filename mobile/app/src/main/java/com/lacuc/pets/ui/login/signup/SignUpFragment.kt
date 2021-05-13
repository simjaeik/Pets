package com.lacuc.pets.ui.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSignUpBinding
import com.lacuc.pets.util.setupWithNavController
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


class SignUpFragment : DaggerFragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SignUpViewModel by viewModels { viewModelFactory }

    private val disposables = CompositeDisposable()

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbarSignUp.setupWithNavController(navController)

        setNameChanges()
        setEmailChanges()
        setNickNameChanges()

        setEmailDuplicateChecker()
        setNickNameDuplicateChecker()

        setPasswordConfirmChecker()

        setOnCompleteEventObserver()
    }

    private fun setNickNameChanges() {
        disposables.add(
            viewModel.bindNickNameChanges(
                binding.textInputSignUpNickName.textChanges()
            )
        )
    }

    private fun setEmailChanges() {
        disposables.add(
            viewModel.bindEmailChanges(
                binding.textInputSignUpEmail.textChanges()
            )
        )
    }

    private fun setNickNameDuplicateChecker() {
        disposables.add(
            viewModel.bindNickNameDuplicate(
                binding.textInputSignUpNickName.textChanges()
            )
        )

        viewModel.isNickNameDuplicated.observe(viewLifecycleOwner) {
            binding.textInputLayoutSignUpNickName.error = it
        }
    }

    private fun setEmailDuplicateChecker() {
        disposables.add(
            viewModel.bindEmailDuplicate(
                binding.textInputSignUpEmail.textChanges()
            )
        )

        viewModel.isEmailDuplicated.observe(viewLifecycleOwner) {
            binding.textInputLayoutSignUpEmail.error = it
        }

    }

    private fun setNameChanges() {
        disposables.add(
            viewModel.bindNameChanges(
                binding.textInputSignUpName.textChanges(),
            )
        )
    }

    private fun setPasswordConfirmChecker() {
        disposables.add(
            viewModel.bindPasswordConfirmError(
                binding.textInputSignUpPassword.textChanges(),
                binding.textInputSignUpPasswordConfirm.textChanges()
            )
        )

        viewModel.passwordConfirmError.observe(viewLifecycleOwner) {
            binding.textInputLayoutSignUpPasswordConfirm.error = it
        }
    }

    private fun setOnCompleteEventObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
        _binding = null
    }
}