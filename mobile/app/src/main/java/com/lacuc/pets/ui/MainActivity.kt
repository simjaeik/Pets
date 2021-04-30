package com.lacuc.pets.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lacuc.pets.databinding.ActivityMainBinding
import com.lacuc.pets.util.SingleLiveEvent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var errorEvent: SingleLiveEvent<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        errorEvent.observe(this, this::showSnackBar)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}