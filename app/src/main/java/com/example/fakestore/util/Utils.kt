package com.example.fakestore.util

object Utils {
    fun extractFirstTwoWords(title: String): String {
        val delimiters = arrayOf(" - ", ", ")
        val words = title.split(*delimiters).flatMap { it.split(" ") }
        return if (words.size >= 2) {
            "${words[0]} ${words[1]}"
        } else {
            title
        }
    }
}