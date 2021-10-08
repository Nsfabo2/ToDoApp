package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class RecyclerViewAdapter(private val tasks: ArrayList<ToDoAppItem>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = tasks[position]

        holder.binding.apply {
            ItemTV.text = item.Task
            ItemCB.isChecked = item.checked
            ItemCB.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    ItemTV.setTextColor(Color.GRAY)
                }else{
                    ItemTV.setTextColor(Color.BLACK)
                }
                item.checked = !item.checked
            }
        }
    }

    override fun getItemCount() = tasks.size

    fun deleteItems(){
        tasks.removeAll{ item -> item.checked }
        notifyDataSetChanged()
    }

}