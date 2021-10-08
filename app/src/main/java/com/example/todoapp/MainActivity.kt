package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/*
Create an application that allows users to add items to a to-do list
The app should use one activity containing a Recycler View and a Floating Action Button
Each Recycler View item should contain a Text View and a Check Box
If the Check Box is checked, the text should be a light color, otherwise, it should be black
Use a ToDo class to keep track of to-do items
The Floating Action Button should trigger an Alert Dialog that contains an Edit Text, a cancel button, and an add button
If the user enters a to-do item and taps the add button, a new instance of the class should be created and added to the Recycler View
 */

class MainActivity : AppCompatActivity() {

    private lateinit var tasks: ArrayList<ToDoAppItem>
    private lateinit var ItemRV: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var floatbtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasks = arrayListOf()

        floatbtn = findViewById<FloatingActionButton>(R.id.AddfloatingActionButton)
        ItemRV = findViewById<RecyclerView>(R.id.ItemRV)
        rvAdapter = RecyclerViewAdapter(tasks)
        ItemRV.adapter = rvAdapter
        ItemRV.layoutManager = LinearLayoutManager(this)

        floatbtn.setOnClickListener {
            TasksDialog()
        }
        title = "To Do App"
    }//end oncreate

    fun TasksDialog(){

        val DialogBuilder = AlertDialog.Builder(this)

        val lLayout = LinearLayout(this)
        lLayout.orientation = LinearLayout.VERTICAL

        val ToDoTask = EditText(this)
        ToDoTask.hint = "whats the task?"
        lLayout.addView(ToDoTask)

        DialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                dialog, id -> tasks.add(ToDoAppItem(ToDoTask.text.toString()))
        })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val DisplayDialog = DialogBuilder.create()
        DisplayDialog.setTitle("New Task")
        DisplayDialog.setView(lLayout)
        DisplayDialog.show()
    }//end tasksdialog

}//end class