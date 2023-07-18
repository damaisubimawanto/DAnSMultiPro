package com.damai.dansmultipro.navigation

import androidx.fragment.app.FragmentActivity
import com.damai.base.navigations.AppNavigation
import com.damai.dansmultipro.ui.home.HomeFragment

/**
 * Created by damai007 on 18/July/2023
 */
class PageNavigationApiImpl(
    private val appNavigation: AppNavigation
) : PageNavigationApi {

    override fun navigateToHomeFragment(fragmentActivity: FragmentActivity, container: Int) {
        appNavigation.pushFragmentClearBackStack(
            fragmentManager = fragmentActivity.supportFragmentManager,
            containerId = container,
            fragment = HomeFragment()
        )
    }
}