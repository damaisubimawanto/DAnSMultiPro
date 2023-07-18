package com.damai.dansmultipro.ui.jobdetail

import com.damai.base.BaseActivity
import com.damai.base.extensions.loadImageWithCenterCrop
import com.damai.base.extensions.observe
import com.damai.base.extensions.showToastMessage
import com.damai.base.utils.Constants.ARGS_JOB_ID
import com.damai.base.utils.EventObserver
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.ActivityJobDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by damai007 on 18/July/2023
 */
class JobDetailActivity : BaseActivity<ActivityJobDetailBinding, JobDetailViewModel>() {

    override val layoutResource: Int = R.layout.activity_job_detail

    override val viewModel: JobDetailViewModel by viewModel()

    override fun ActivityJobDetailBinding.viewInitialization() {
        intent.extras?.let { bundle ->
            if (bundle.containsKey(ARGS_JOB_ID)) {
                viewModel.jobId = bundle.getString(ARGS_JOB_ID)
            }
        }
        companyProfile.vm = viewModel
        companyProfile.lifecycleOwner = this@JobDetailActivity

        jobSpecification.vm = viewModel
        jobSpecification.lifecycleOwner = this@JobDetailActivity
    }

    override fun ActivityJobDetailBinding.setupObservers() {
        observe(viewModel.jobDetailLiveData) {
            companyProfile.ivCompanyLogo.loadImageWithCenterCrop(url = it.companyLogo)
        }

        observe(viewModel.jobDetailErrorLiveData, EventObserver { errorMessage ->
            if (errorMessage.isNotBlank()) {
                showToastMessage(message = errorMessage)
            }
            finish()
        })
    }

    override fun ActivityJobDetailBinding.onPreparationFinished() {
        if (viewModel.jobDetailLiveData.value == null) {
            viewModel.getJobDetail()
        }
    }
}