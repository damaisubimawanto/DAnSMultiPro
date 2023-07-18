package com.damai.dansmultipro.ui

import com.damai.base.BaseActivity
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.ActivityMainBinding
import com.damai.dansmultipro.navigation.PageNavigationApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by damai007 on 18/July/2023
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val pageNavigationApi: PageNavigationApi by inject()

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun ActivityMainBinding.viewInitialization() {
        pageNavigationApi.navigateToHomeFragment(
            fragmentActivity = this@MainActivity,
            container = flMainContent.id
        )
    }
}