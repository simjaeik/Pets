package com.lacuc.pets.ui.login.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.FragmentSignInBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SignInFragment : DaggerFragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SignInViewModel by viewModels { viewModelFactory }

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnCompleteObserver()
        setSignUpListener()
    }

    private fun setOnCompleteObserver() {
        viewModel.completeEvent.observe(viewLifecycleOwner) {
            val action = SignInFragmentDirections.actionSignInFragmentToMainActivity()
            navController.navigate(action)
            requireActivity().finish()
        }
    }

    private fun setSignUpListener() {
        binding.btnSignInSignUp.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}