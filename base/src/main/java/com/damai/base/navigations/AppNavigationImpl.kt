package com.damai.base.navigations

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

/**
 * Created by damai007 on 18/July/2023
 */
class AppNavigationImpl : AppNavigation {

    override fun pushFragmentClearBackStack(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment
    ) = with(fragmentManager) {
        commit {
            if (fragments.isNotEmpty()) fragments.forEach { detach(it) }
            replace(containerId, fragment)
        }
    }

    override fun pushReplaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        fragmentTag: String?
    ) = with(fragmentManager) {
        commit {
            fragmentTag.takeIf { it.isNullOrEmpty().not() }?.let { tag ->
                replace(containerId, fragment, tag)
                addToBackStack(tag)
            } ?: add(containerId, fragment)
        }
    }
}