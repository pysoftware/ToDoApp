package com.magere.to_do.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todolist_table")
    fun loadAllPersons(): LiveData<List<ToDoEntity>>

    @Query("SELECT * FROM todolist_table WHERE id = :id")
    fun loadByPersonId(id: Int): ToDoEntity

    @Insert
    fun insertPerson(todo: ToDoEntity)

    @Update
    fun updatePerson(todo: ToDoEntity)

    @Query("DELETE FROM todolist_table WHERE id = :id")
    fun deletePerson(id: Int?)

    @Query("DELETE FROM todolist_table WHERE id >= 0")
    fun deleteAll()

}