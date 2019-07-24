package com.magere.to_do.adapters

import com.magere.to_do.data.db.ToDoEntity

interface ItemTouchListener {
    fun onItemClick(position: Int)

    fun onRightMenuClick(id: Int?)
}