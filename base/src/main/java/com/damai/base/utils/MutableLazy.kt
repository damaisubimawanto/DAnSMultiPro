package com.damai.base.utils

/**
 * Created by damai007 on 18/July/2023
 *
 * Big thanks to Kofi for his answer in StackOverflow.
 * @see <a href="https://stackoverflow.com/a/61461551/4569155">Reference</a>
 */
class MutableLazy<T>(private val initializer: () -> T) : Lazy<T> {

    private var cached: T? = null

    override val value: T
        get() {
            if (cached == null) {
                cached = initializer()
            }
            @Suppress("UNCHECKED_CAST")
            return cached as T
        }

    override fun isInitialized(): Boolean = cached != null

    fun reset() {
        cached = null
    }

    companion object {
        fun <T> resettableLazy(value: () -> T) = MutableLazy(value)
    }
}