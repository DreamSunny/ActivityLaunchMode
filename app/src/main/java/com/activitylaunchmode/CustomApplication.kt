package com.activitylaunchmode

import android.app.Application
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yhao.floatwindow.FloatWindow
import com.yhao.floatwindow.Screen
import me.weishu.reflection.Reflection

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Reflection.unseal(this)

        val recyclerView = RecyclerView(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = CustomAdapter(mutableListOf())

        FloatWindow
            .with(applicationContext)
            .setTag("lifecycle")
            .setView(recyclerView)
            .setWidth(Screen.width, 0.6f)
            .setHeight(Screen.height, 0.5f)
            .setX(0)
            .setY(Screen.height, 0.5f)
            .setMoveType(0)
            .setDesktopShow(false)
            .build()

        FloatWindow.get("lifecycle").show()

        val textView = TextView(applicationContext)
        textView.gravity = Gravity.END
        textView.setTextColor(resources.getColor(android.R.color.holo_red_light, null))

        FloatWindow
            .with(applicationContext)
            .setTag("tasks")
            .setView(textView)
            .setWidth(Screen.width, 0.4f)
            .setHeight(Screen.height, 0.5f)
            .setX(Screen.width, 0.6f)
            .setY(Screen.height, 0.5f)
            .setMoveType(0)
            .setDesktopShow(false)
            .build()

        FloatWindow.get("tasks").show()
    }
}

class CustomAdapter(private val dataSet: MutableList<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size

    fun insertData(data: String) {
        dataSet.add(data)
        notifyItemInserted(dataSet.size)
    }
}
