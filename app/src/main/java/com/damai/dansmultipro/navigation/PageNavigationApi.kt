package com.damai.dansmultipro.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by damai007 on 18/July/2023
 */
interface PageNavigationApi {

    fun selectFragment(
        selectedFragment: Fragment,
        fragmentActivity: FragmentActivity,
        @IdRes container: Int,
        tag: String
    )

    fun navigateToHomeFragment(
        fragmentActivity: FragmentActivity,
        @IdRes container: Int
    )
}