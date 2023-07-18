package com.damai.base.navigations

import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by damai007 on 18/July/2023
 */
interface AppNavigation {

    /**
     * Push a fragment to [FragmentManager] by clearing all the backstack
     */
    fun pushFragmentClearBackStack(
        fragmentManager: FragmentManager,
        @IdRes containerId: Int,
        fragment: Fragment
    )

    /**
     * Push a fragment to [FragmentManager] using [FragmentTransaction.replace] if added into backstack previous
     * fragment will be re-created [Fragment.onCreate]
     *
     * @param fragmentManager either [AppCompatActivity.getSupportFragmentManager] or [Fragment.getChildFragmentManager]
     * @param containerId view container id usually [FrameLayout]
     * @param fragment that will going to attach
     * @param fragmentTag will add to [FragmentTransaction.addToBackStack] if not empty
     */
    fun pushReplaceFragment(
        fragmentManager: FragmentManager,
        @IdRes containerId: Int,
        fragment: Fragment,
        fragmentTag: String? = null
    )
}