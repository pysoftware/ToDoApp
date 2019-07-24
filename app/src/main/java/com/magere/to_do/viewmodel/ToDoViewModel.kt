package com.magere.to_do.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.magere.to_do.data.db.ToDoEntity
import com.magere.to_do.repository.ToDoRepository

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val personRepository = ToDoRepository(application)
    private var allPersons: LiveData<List<ToDoEntity>>? = personRepository.getAllToDo()

    fun insert(toDo: ToDoEntity) {
        personRepository.insertToDo(toDo)
    }

    fun update(toDo: ToDoEntity) {
        personRepository.updateToDo(toDo)
    }

    fun delete(id: Int?) {
        personRepository.deleteToDo(id)
    }

    fun getAllPersons(): LiveData<List<ToDoEntity>>? {
        return allPersons
    }

    fun deleteAll() {
        personRepository.deleteAll()
    }

}