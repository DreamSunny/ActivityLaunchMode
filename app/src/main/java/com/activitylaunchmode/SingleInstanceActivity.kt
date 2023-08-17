package com.activitylaunchmode

import android.os.Bundle

class SingleInstanceActivity : BaseActivity() {
    companion object {
        var index = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "SingleInstance-${index++}"
        super.onCreate(savedInstanceState)
    }
}