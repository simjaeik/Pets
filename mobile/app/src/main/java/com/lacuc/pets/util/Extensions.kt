package com.lacuc.pets.util

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

fun Toolbar.setupWithNavController(navController: NavController) {
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    setupWithNavController(navController, appBarConfiguration)
}

fun <T : RecyclerView.ViewHolder> RecyclerView.setup(adapter: RecyclerView.Adapter<T>) {
    this.apply {
        this.adapter = adapter
        layoutManager = DefaultLayoutManager(context)
    }
}

fun TabLayout.doOnTabSelectedListener(action: (TabLayout.Tab) -> Unit) {
    val listener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            action.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    }
    addOnTabSelectedListener(listener)
}