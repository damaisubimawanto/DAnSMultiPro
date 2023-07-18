package com.damai.dansmultipro.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.damai.base.navigations.AppNavigation
import com.damai.dansmultipro.ui.home.HomeFragment

/**
 * Created by damai007 on 18/July/2023
 */
class PageNavigationApiImpl(
    private val appNavigation: AppNavigation
) : PageNavigationApi {

    override fun selectFragment(
        selectedFragment: Fragment,
        fragmentActivity: FragmentActivity,
        container: Int,
        tag: String
    ) {
        val fragmentTransaction = fragmentActivity.supportFragmentManager.beginTransaction()

        val currentFragment = fragmentActivity.supportFragmentManager.primaryNavigationFragment
        if (currentFragment != null && currentFragment != selectedFragment) {
            fragmentTransaction.detach(currentFragment)
        }

        var fragment = fragmentActivity.supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = selectedFragment
            fragmentTransaction.add(container, fragment, tag)
        } else {
            fragmentTransaction.attach(fragment)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commit()
    }

    override fun navigateToHomeFragment(fragmentActivity: FragmentActivity, container: Int) {
        appNavigation.pushFragmentClearBackStack(
            fragmentManager = fragmentActivity.supportFragmentManager,
            containerId = container,
            fragment = HomeFragment()
        )
    }
}