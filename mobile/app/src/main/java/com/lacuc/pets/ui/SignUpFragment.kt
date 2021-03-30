package com.lacuc.pets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSignUpBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels { ViewModelFactory() }

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        disposables.add(
            viewModel.bindPasswordConfirmError(
                binding.textInputPassword.textChanges(),
                binding.textInputPasswordConfirm.textChanges()
            )
        )

        disposables.add(
            viewModel.bindCompleteBtnEnable(
                binding.textInputName.textChanges(),
                binding.textInputEmail.textChanges(),
                binding.textInputPassword.textChanges(),
                binding.textInputPasswordConfirm.textChanges()
            )
        )

        viewModel.passwordConfirmError.observe(viewLifecycleOwner) {
            binding.inputLayoutPasswordConfirm.error = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
        _binding = null
    }
}