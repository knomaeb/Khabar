package com.example.khabar.core.logger

import android.util.Log

class DefaultLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}