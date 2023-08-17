package com.activitylaunchmode

import android.os.Bundle

class SingleInstancePerTaskActivity : BaseActivity() {
    companion object {
        var index = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "SingleInstancePerTask-${index++}"
        super.onCreate(savedInstanceState)
    }
}