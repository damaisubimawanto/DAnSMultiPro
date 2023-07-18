package com.damai.dansmultipro.ui.profile

import com.damai.base.BaseFragment
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.FragmentProfileBinding
import com.damai.dansmultipro.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * Created by damai007 on 18/July/2023
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding, MainViewModel>() {

    override val layoutResource: Int = R.layout.fragment_profile

    override val viewModel: MainViewModel by activityViewModel()

    companion object {
        const val TAG = "ProfileFragment"

        fun newInstance() = ProfileFragment()
    }
}