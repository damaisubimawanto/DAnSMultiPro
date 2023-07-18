package com.damai.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by damai007 on 18/July/2023
 */
abstract class BaseListAdapter<VB: ViewDataBinding, DATA> constructor(
    diffUtil: DiffUtil.ItemCallback<DATA>
): ListAdapter<DATA, BaseViewHolder<VB, DATA>>(diffUtil) {

    override fun onBindViewHolder(holder: BaseViewHolder<VB, DATA>, position: Int) {
        holder.bind(data = getItem(position))
    }
}