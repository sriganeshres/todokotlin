package com.sriganesh.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todoitems: MutableList<TodoItem>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todoitems[position]
        holder.itemView.apply {
            val todoTitle = findViewById<TextView>(R.id.tvTodoTitle)
            val todoIsChecked = findViewById<CheckBox>(R.id.cbDone)
            toggleStrikeThrough(todoTitle, todoIsChecked.isChecked)
            todoIsChecked.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitle, isChecked)
                currTodo.isChecked = !currTodo.isChecked
            }
            todoTitle.text = currTodo.title
            todoIsChecked.isChecked = currTodo.isChecked
        }
    }
    fun addTodo(todoItem: TodoItem){
        todoitems.add(todoItem)
        notifyItemInserted(todoitems.size - 1)
    }

    fun deleteDonetodos() {
        todoitems.removeAll { todoItem ->
            todoItem.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return todoitems.size
    }
}