package com.damai.base.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by damai007 on 18/July/2023
 */
abstract class EndlessScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val visibleThreshold: Int = 2
) : RecyclerView.OnScrollListener() {

    private var currentPage = 1
    private var previousTotalItemCount = 0
    private var loading = true

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = 1
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
            onLoadMore(++currentPage, totalItemCount, view)
            loading = true
        }
    }

    fun resetScrolling() {
        loading = true
        currentPage = 1
        previousTotalItemCount = 0
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}