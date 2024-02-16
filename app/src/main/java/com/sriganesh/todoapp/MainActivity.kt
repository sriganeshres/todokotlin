package com.sriganesh.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriganesh.todoapp.ui.theme.TodoappTheme

class MainActivity : ComponentActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        val recycleView = findViewById<RecyclerView>(R.id.rvTodoList)
        recycleView.adapter = todoAdapter
        recycleView.layoutManager = LinearLayoutManager(this)

        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val btnDeleteDoneTodo = findViewById<Button>(R.id.btnDeleteDoneTodo)

        btnAddTodo.setOnClickListener{
            val todoTitle = findViewById<EditText>(R.id.etTodoName).text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = TodoItem(todoTitle)
                todoAdapter.addTodo(todo)
                findViewById<EditText>(R.id.etTodoName).text.clear()
            }
        }

        btnDeleteDoneTodo.setOnClickListener{
            todoAdapter.deleteDonetodos()
        }
    }
}