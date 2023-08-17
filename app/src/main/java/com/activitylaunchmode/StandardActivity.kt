package com.activitylaunchmode

import android.os.Bundle

class StandardActivity : BaseActivity() {
    companion object {
        var index = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Standard-${index++}"
        super.onCreate(savedInstanceState)
    }
}