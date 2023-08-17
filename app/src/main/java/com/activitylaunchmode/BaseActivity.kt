package com.activitylaunchmode

import android.app.ActivityManager
import android.app.TaskInfo
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yhao.floatwindow.FloatWindow

open class BaseActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logLifecycle("onNewIntent")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.standard).setOnClickListener {
            startActivity(Intent(this, StandardActivity::class.java))
        }
        findViewById<Button>(R.id.singleTop).setOnClickListener {
            startActivity(Intent(this, SingleTopActivity::class.java))
        }
        findViewById<Button>(R.id.singleTask).setOnClickListener {
            startActivity(Intent(this, SingleTaskActivity::class.java))
        }
        findViewById<Button>(R.id.singleInstance).setOnClickListener {
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }
        findViewById<Button>(R.id.singleInstancePerTask).setOnClickListener {
            startActivity(Intent(this, SingleInstancePerTaskActivity::class.java))
        }
        logLifecycle("onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
        logTasks()
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    private fun logLifecycle(method: String) {
        val recyclerView = FloatWindow.get("lifecycle").view as RecyclerView
        val customAdapter = recyclerView.adapter as CustomAdapter
        customAdapter.insertData("$title: $method")
        recyclerView.scrollToPosition(customAdapter.itemCount - 1)
    }

    private fun logTasks() {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val text = am.getRunningTasks(5).joinToString(separator = "\n\n") {
            "Task ${it.taskId}: ${it.numActivities}\n" +
                    "R=${it["isRunning"]} F=${it["isFocused"]} V=${it["isVisible"]}\n" +
                    "topA=${it.topActivity?.shortClassName}"
        }
        (FloatWindow.get("tasks").view as TextView).text = text
    }
}

operator fun TaskInfo.get(field: String): Boolean {
    return this::class.java.getField(field).get(this) as Boolean
}