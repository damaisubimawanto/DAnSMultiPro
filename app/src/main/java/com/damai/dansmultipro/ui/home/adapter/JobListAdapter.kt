package com.damai.dansmultipro.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.damai.base.BaseListAdapter
import com.damai.base.BaseViewHolder
import com.damai.base.extensions.loadImageWithCenterCrop
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.ItemRvJobListBinding
import com.damai.domain.models.JobPositionModel

/**
 * Created by damai007 on 18/July/2023
 */
class JobListAdapter(
    private val callback: (JobPositionModel) -> Unit
) : BaseListAdapter<ItemRvJobListBinding, JobPositionModel>(
    diffUtil = JobComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRvJobListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_rv_job_list,
            parent,
            false
        )
        return ViewHolder(dataBinding = binding)
    }

    inner class ViewHolder(
        dataBinding: ItemRvJobListBinding
    ): BaseViewHolder<ItemRvJobListBinding, JobPositionModel>(binding = dataBinding) {

        override fun bind(data: JobPositionModel) {
            with(binding) {
                ivCompanyLogo.loadImageWithCenterCrop(url = data.companyLogo)

                model = data
                executePendingBindings()

                clMainView.setCustomOnClickListener {
                    callback.invoke(data)
                }
            }
        }
    }
}