package com.lacuc.pets.ui.manage

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.lacuc.pets.R
import com.lacuc.pets.ViewModelFactory
import com.lacuc.pets.databinding.ActivityManageBinding
import com.lacuc.pets.util.SingleLiveEvent
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class ManageActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityManageBinding

    @Inject
    lateinit var errorEvent: SingleLiveEvent<String>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ManageViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigationBackStackChangeListener()

        errorEvent.observe(this, this::showSnackBar)
    }

    private fun setNavigationBackStackChangeListener() {
        val childFM =
            (supportFragmentManager.findFragmentById(R.id.container_navHost) as NavHostFragment).childFragmentManager
        childFM.addOnBackStackChangedListener {
            Timber.d("BackStack count: ${childFM.backStackEntryCount}")
            for (i in 0 until childFM.backStackEntryCount) {
                Timber.d("BackStack($i): ${childFM.getBackStackEntryAt(i).name}")
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}