package com.magere.to_do.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magere.to_do.R
import com.magere.to_do.data.db.ToDoEntity
import kotlinx.android.synthetic.main.swipe_content.view.*
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoListAdapter(private var mItemTouchListener: ItemTouchListener?) :
    RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    private var mToDoList = mutableListOf<ToDoEntity>()

    fun setData(toDoList: List<ToDoEntity>) {
        mToDoList.clear()
        mToDoList.addAll(toDoList)

        notifyDataSetChanged()
    }

    fun insert(toDoEntity: ToDoEntity) {
        mToDoList.add(toDoEntity)
        notifyItemInserted(mToDoList.size - 1)
    }

    private fun deleteToDoForSwipe(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        mToDoList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return mToDoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = mToDoList[position])

        holder.itemView.setOnClickListener {
            mItemTouchListener?.onItemClick(position)
        }
        holder.leftMenu.setOnClickListener {
            mItemTouchListener?.onRightMenuClick(mToDoList[position].id)
            deleteToDoForSwipe(holder)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftMenu: TextView = itemView.left_menu

        fun bind(model: ToDoEntity) {
            itemView.swipe_layout.isSwipeEnable = true
            itemView.tv_title.text = model.title
            when (model.priority) {
                0 -> itemView.iv_priority.setImageResource(R.drawable.ic_circle_green)
                1 -> itemView.iv_priority.setImageResource(R.drawable.ic_circle_yellow)
                2 -> itemView.iv_priority.setImageResource(R.drawable.ic_circle_red)
                else -> itemView.iv_priority.setImageResource(0)
            }
        }
    }
}