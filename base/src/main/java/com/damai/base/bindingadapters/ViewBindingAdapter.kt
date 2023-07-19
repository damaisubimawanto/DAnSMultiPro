package com.damai.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import com.damai.base.extensions.loadImageWithCenterCrop

/**
 * Created by damai007 on 19/July/2023
 */
object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String?) {
        view.loadImageWithCenterCrop(url = url)
    }

    @JvmStatic
    @BindingAdapter("renderHtml")
    fun bindRenderHtml(view: AppCompatTextView, description: String?) {
        val htmlText = description.takeIf { it.isNullOrBlank().not() }?.let {
            HtmlCompat.fromHtml(it, FROM_HTML_MODE_COMPACT)
        }
        view.text = htmlText
    }
}