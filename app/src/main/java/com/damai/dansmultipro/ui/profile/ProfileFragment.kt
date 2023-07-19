package com.damai.dansmultipro.ui.profile

import com.damai.base.BaseFragment
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.base.extensions.showToastMessage
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

    override fun FragmentProfileBinding.viewInitialization() {
        vm = viewModel
        lifecycleOwner = this@ProfileFragment
    }

    override fun FragmentProfileBinding.setupListeners() {
        btnLogout.setCustomOnClickListener {
            // TODO: Log out the account preferences
            requireActivity().showToastMessage(
                message = getString(R.string.under_development_message)
            )
        }
    }

    override fun FragmentProfileBinding.onPreparationFinished() {
        if (viewModel.accountModelLiveData.value == null) {
            viewModel.dummyAccount()
        }
    }
}