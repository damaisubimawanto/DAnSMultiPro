package com.damai.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import com.damai.base.R
import com.damai.base.extensions.loadImageWithCenterCrop
import com.damai.base.extensions.loadImageWithCircleCrop

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
    @BindingAdapter("loadImageCircle")
    fun bindLoadImageCircle(view: AppCompatImageView, url: String?) {
        view.loadImageWithCircleCrop(url = url)
    }

    @JvmStatic
    @BindingAdapter("renderHtml")
    fun bindRenderHtml(view: AppCompatTextView, description: String?) {
        val htmlText = description.takeIf { it.isNullOrBlank().not() }?.let {
            HtmlCompat.fromHtml(it, FROM_HTML_MODE_COMPACT)
        }
        view.text = htmlText
    }

    @JvmStatic
    @BindingAdapter("fullTime")
    fun bindFullTimeText(view: AppCompatTextView, fullType: String?) {
        when {
            (fullType?.contains(other = "full time", ignoreCase = true) == true) ||
                    (fullType?.contains(other = "fulltime", ignoreCase = true) == true) -> {
                view.text = view.context.getString(R.string.yes)
            }
            else -> view.text = view.context.getString(R.string.no)
        }
    }
}