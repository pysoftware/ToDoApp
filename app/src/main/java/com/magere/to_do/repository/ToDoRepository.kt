package com.magere.to_do.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.magere.to_do.data.db.Database
import com.magere.to_do.data.db.ToDoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToDoRepository(application: Application) {
    private val db = Database.getInstance(application)
    private val toDoDao = db?.toDoDao()
    private var allToDo: LiveData<List<ToDoEntity>>? = toDoDao?.loadAllPersons()

    fun insertToDo(person: ToDoEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            toDoDao?.insertPerson(person)
        }
    }

    fun updateToDo(person: ToDoEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            toDoDao?.updatePerson(person)
        }
    }

    fun deleteToDo(id: Int?) {
        GlobalScope.launch(Dispatchers.IO) {
            delay(500)
            toDoDao?.deletePerson(id)
        }
    }

    fun getAllToDo(): LiveData<List<ToDoEntity>>? {
        return allToDo
    }

    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            toDoDao?.deleteAll()
        }
    }
}