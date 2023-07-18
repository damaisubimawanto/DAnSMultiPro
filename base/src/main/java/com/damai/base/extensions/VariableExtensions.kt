package com.damai.base.extensions

import java.util.Timer
import java.util.TimerTask

/**
 * Created by damai007 on 19/July/2023
 */

fun Int?.orZero() = this ?: 0

fun Timer.scheduleAfter(
    delay: Long,
    process: () -> Unit
): Timer {
    schedule(object : TimerTask() {
        override fun run() {
            process.invoke()
        }
    }, delay)
    return this
}