package se.springer.geotrain.components

import androidx.navigation.NavController

object NavigationController {
    private var navController: NavController? = null

    fun setNavController(controller: NavController) {
        navController = controller
    }

    fun navigate(string: String) {
        navController?.navigate(string)
    }
}