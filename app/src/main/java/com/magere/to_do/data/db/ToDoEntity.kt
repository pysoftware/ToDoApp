package com.magere.to_do.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoList_table")
class ToDoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val date: Long? = null,
    val priority: Int? = 0,
    val status: Int? = 0
)