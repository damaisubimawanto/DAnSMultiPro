package com.damai.dansmultipro.ui.home

import com.damai.base.BaseFragment
import com.damai.base.extensions.observe
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.FragmentHomeBinding
import com.damai.dansmultipro.ui.MainViewModel
import com.damai.dansmultipro.ui.home.adapter.JobListAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * Created by damai007 on 18/July/2023
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private lateinit var mJobListAdapter: JobListAdapter

    override val layoutResource: Int = R.layout.fragment_home

    override val viewModel: MainViewModel by activityViewModel()

    override fun FragmentHomeBinding.viewInitialization() {
        with(rvJobList) {
            mJobListAdapter = JobListAdapter()
            adapter = mJobListAdapter
        }
    }

    override fun FragmentHomeBinding.setupObservers() {
        observe(viewModel.jobPositionListLiveData) {
            mJobListAdapter.submitList(it)
        }
    }

    override fun FragmentHomeBinding.onPreparationFinished() {
        if (viewModel.jobPositionListLiveData.value.isNullOrEmpty()) {
            viewModel.getJobPositionList()
        }
    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }
}