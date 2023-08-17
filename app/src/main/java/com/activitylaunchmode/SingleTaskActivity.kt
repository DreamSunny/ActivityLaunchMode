package com.activitylaunchmode

import android.os.Bundle

class SingleTaskActivity : BaseActivity() {
    companion object {
        var index = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "SingleTask-${index++}"
        super.onCreate(savedInstanceState)
    }
}