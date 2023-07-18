package com.damai.dansmultipro.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.damai.domain.models.JobPositionModel

/**
 * Created by damai007 on 18/July/2023
 */
object JobComparator : DiffUtil.ItemCallback<JobPositionModel>() {

    override fun areItemsTheSame(oldItem: JobPositionModel, newItem: JobPositionModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JobPositionModel, newItem: JobPositionModel): Boolean {
        return oldItem == newItem
    }
}