package com.lacuc.pets.util

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

fun setupToolbar(navController: NavController, toolbar: Toolbar) {
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    toolbar.setupWithNavController(navController, appBarConfiguration)
}