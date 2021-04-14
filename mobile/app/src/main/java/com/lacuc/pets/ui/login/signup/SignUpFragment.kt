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
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSignUp.setupWithNavController(navController)

        setPasswordConfirmErrorMessage()

        setCompleteBtnEnable()
    }

    private fun setCompleteBtnEnable() {
        disposables.add(
            viewModel.bindCompleteBtnEnable(
                binding.textInputSignUpName.textChanges(),
                binding.textInputSignUpEmail.textChanges(),
                binding.textInputSignUpPassword.textChanges(),
                binding.textInputSignUpPasswordConfirm.textChanges()
            )
        )
    }

    private fun setPasswordConfirmErrorMessage() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
        _binding = null
    }
}