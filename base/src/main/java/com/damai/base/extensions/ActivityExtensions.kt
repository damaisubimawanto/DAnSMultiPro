package com.damai.base.extensions

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.damai.base.utils.Event
import com.damai.base.utils.EventObserver

/**
 * Created by damai007 on 18/July/2023
 */

fun <T> FragmentActivity.observe(
    liveData: LiveData<T>,
    action: (t: T) -> Unit
) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

@JvmName("observeEvent")
fun <T> FragmentActivity.observe(
    liveData: LiveData<Event<T>>,
    observer: EventObserver<T>
) {
    liveData.observe(this, observer)
}

fun FragmentActivity.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}