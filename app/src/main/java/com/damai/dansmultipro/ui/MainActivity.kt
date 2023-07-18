package com.damai.dansmultipro.ui

import android.view.MenuItem
import com.damai.base.BaseActivity
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.ActivityMainBinding
import com.damai.dansmultipro.navigation.PageNavigationApi
import com.damai.dansmultipro.ui.home.HomeFragment
import com.damai.dansmultipro.ui.profile.ProfileFragment
import com.google.android.material.navigation.NavigationBarView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by damai007 on 18/July/2023
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    NavigationBarView.OnItemSelectedListener {

    private val pageNavigationApi: PageNavigationApi by inject()

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun ActivityMainBinding.viewInitialization() {
        with(bottomNavigationView) {
            itemIconTintList = null
            setOnItemSelectedListener(this@MainActivity)
            selectedItemId = R.id.navigation_home
        }
    }

    /**
     * Callback from NavigationBarView.OnItemSelectedListener.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_profile -> {
                // TODO: Handle if user is not login, then return false

                pageNavigationApi.selectFragment(
                    selectedFragment = ProfileFragment.newInstance(),
                    fragmentActivity = this@MainActivity,
                    container = binding.flMainContent.id,
                    tag = ProfileFragment.TAG
                )
                true
            }

            else -> {
                pageNavigationApi.selectFragment(
                    selectedFragment = HomeFragment.newInstance(),
                    fragmentActivity = this@MainActivity,
                    container = binding.flMainContent.id,
                    tag = HomeFragment.TAG
                )
                true
            }
        }
    }
}