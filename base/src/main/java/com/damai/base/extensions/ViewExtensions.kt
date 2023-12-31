package com.damai.base.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.request.RequestOptions
import com.damai.base.R
import com.damai.base.glides.GlideApp

/**
 * Created by damai007 on 18/July/2023
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setCustomOnClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : View.OnClickListener {
        var lastTimeClicked = 0L

        override fun onClick(p0: View?) {
            if (System.currentTimeMillis() - lastTimeClicked > 1_500L) {
                lastTimeClicked = System.currentTimeMillis()
                listener.onClick(p0)
            }
        }
    })
}

fun AppCompatEditText.addOnTextChanged(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            callback.invoke(p0?.toString().orEmpty())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {}
    })
}

fun AppCompatImageView.loadImageWithCenterCrop(
    url: String?
) {
    GlideApp.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_broken_image_48px)
        .into(this)
}

fun AppCompatImageView.loadImageWithCircleCrop(
    url: String?
) {
    GlideApp.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .error(R.drawable.ic_broken_image_48px)
        .into(this)
}