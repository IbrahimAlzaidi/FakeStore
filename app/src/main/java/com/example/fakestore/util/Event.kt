package com.example.fakestore.util

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
    fun getContentIfNotHandle(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}