package com.damai.dansmultipro.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.damai.base.BaseViewHolder
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.dansmultipro.R
import com.damai.dansmultipro.databinding.ItemRvJobListBinding
import com.damai.dansmultipro.databinding.ItemRvLoadmoreBinding
import com.damai.domain.models.JobPositionModel

/**
 * Created by damai007 on 18/July/2023
 */
class JobListAdapter(
    private var data: List<JobPositionModel>,
    private var isLoadMoreShowing: Boolean = true,
    private val callback: (JobPositionModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val DATA = 0
        private const val LOAD_MORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD_MORE -> {
                val binding = DataBindingUtil.inflate<ItemRvLoadmoreBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_rv_loadmore,
                    parent,
                    false
                )
                LoadMoreViewHolder(dataBinding = binding)
            }

            else -> {
                val binding = DataBindingUtil.inflate<ItemRvJobListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_rv_job_list,
                    parent,
                    false
                )
                ViewHolder(dataBinding = binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(data = data[position])
            is LoadMoreViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) {
            LOAD_MORE
        } else {
            DATA
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<JobPositionModel>) {
        data = newData
        notifyDataSetChanged()
    }

    fun setLoadMoreVisibility(isLoadMoreShowing: Boolean) {
        this.isLoadMoreShowing = isLoadMoreShowing
        notifyItemChanged(data.size)
    }

    inner class ViewHolder(
        dataBinding: ItemRvJobListBinding
    ): BaseViewHolder<ItemRvJobListBinding, JobPositionModel>(binding = dataBinding) {

        override fun bind(data: JobPositionModel) {
            with(binding) {
                model = data
                executePendingBindings()

                clMainView.setCustomOnClickListener {
                    callback.invoke(data)
                }
            }
        }
    }

    inner class LoadMoreViewHolder(
        private val dataBinding: ItemRvLoadmoreBinding
    ): RecyclerView.ViewHolder(dataBinding.root) {

        fun bind() {
            dataBinding.progressBarLoadMore.isVisible = isLoadMoreShowing
        }
    }
}