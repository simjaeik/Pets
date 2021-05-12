package com.lacuc.pets.ui.login

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lacuc.pets.databinding.ActivityLoginBinding
import com.lacuc.pets.util.SingleLiveEvent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var errorEvent: SingleLiveEvent<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        errorEvent.observe(this, this::showSnackBar)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}