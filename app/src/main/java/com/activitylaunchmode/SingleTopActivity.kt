package com.activitylaunchmode

import android.os.Bundle

class SingleTopActivity : BaseActivity() {
    companion object {
        var index = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "SingleTop-${index++}"
        super.onCreate(savedInstanceState)
    }
}