package com.lacuc.pets.ui.login.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lacuc.pets.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSignUpListener()
        setSignInListener()
    }

    private fun setSignInListener() {
        binding.btnSignInSignIn.setOnClickListener {
            // TODO: 2021-04-02 API가 준비되면 로그인을 요청하고 결과값에 따라 이동해야 함.
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