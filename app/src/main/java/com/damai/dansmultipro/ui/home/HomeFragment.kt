package com.damai.dansmultipro.ui.home

import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.damai.base.BaseFragment
import com.damai.base.extensions.addOnTextChanged
import com.damai.base.extensions.checkIfFragmentNotAttachToActivity
import com.damai.base.extensions.observe
import com.damai.base.extensions.orZero
import com.damai.base.extensions.scheduleAfter
import com.damai.base.utils.EndlessScrollListener
import com.damai.base.utils.MutableLazy
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.FragmentHomeBinding
import com.damai.dansmultipro.navigation.PageNavigationApi
import com.damai.dansmultipro.ui.MainViewModel
import com.damai.dansmultipro.ui.home.adapter.JobListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.Timer

/**
 * Created by damai007 on 18/July/2023
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private lateinit var mJobListAdapter: JobListAdapter

    private var mTimer: Timer? = null

    private val _endlessScrollListenerDelegate = MutableLazy.resettableLazy {
        val layoutManager = binding.rvJobList.layoutManager as LinearLayoutManager
        object : EndlessScrollListener(
            layoutManager = layoutManager,
            visibleThreshold = 3
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (viewModel.isPaginationContinue) {
                    viewModel.pagination = page
                    if (binding.etSearch.text?.length.orZero() > 3) {
                        viewModel.getJobPositionListByFilter(
                            keyword = binding.etSearch.text?.toString()
                        )
                    } else {
                        viewModel.getJobPositionList()
                    }
                }
            }
        }
    }

    private val mEndlessScrollListener: EndlessScrollListener by _endlessScrollListenerDelegate

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            /* Do nothing. */
        }

    private val pageNavigationApi: PageNavigationApi by inject()

    override val layoutResource: Int = R.layout.fragment_home

    override val viewModel: MainViewModel by activityViewModel()

    override fun FragmentHomeBinding.viewInitialization() {
        with(rvJobList) {
            mJobListAdapter = JobListAdapter {
                pageNavigationApi.navigateToJobDetailActivity(
                    context = requireContext(),
                    launcher = activityLauncher,
                    jobId = it.id
                )
            }
            adapter = mJobListAdapter
            clearOnScrollListeners()
            /* The endless should be re-init */
            _endlessScrollListenerDelegate.reset()
            addOnScrollListener(mEndlessScrollListener)
        }
    }

    override fun FragmentHomeBinding.setupListeners() {
        fun reset() {
            viewModel.isResetList = true
            viewModel.pagination = 1
        }

        etSearch.addOnTextChanged {
            fun doHitJobPositionListByFilter() {
                requireActivity().runOnUiThread {
                    reset()
                    viewModel.getJobPositionListByFilter(keyword = it)
                }
            }

            mTimer?.cancel()
            val textLength = it.length

            when {
                textLength > 3 -> {
                    mTimer = Timer().scheduleAfter(delay = 500L) {
                        if (checkIfFragmentNotAttachToActivity()) return@scheduleAfter
                        doHitJobPositionListByFilter()
                    }
                }

                textLength == 0 -> {
                    reset()
                    viewModel.getJobPositionList()
                }
            }
        }
    }

    override fun FragmentHomeBinding.setupObservers() {
        observe(viewModel.jobPositionListLiveData) {
            if (viewModel.isResetList) {
                viewModel.isResetList = false
                mJobListAdapter.submitList(null)
                mEndlessScrollListener.resetScrolling()
            }
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