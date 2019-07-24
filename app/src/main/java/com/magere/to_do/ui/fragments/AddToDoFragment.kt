package com.magere.to_do.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.magere.to_do.R
import com.magere.to_do.adapters.ToDoListAdapter
import com.magere.to_do.data.db.ToDoEntity
import com.magere.to_do.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add_to_do.*

class AddToDoFragment : Fragment() {

    private lateinit var personViewModel: ToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personViewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        accept_add_to_do.setOnClickListener {
            val toDo = ToDoEntity(
                null,
                et_title.text.toString(),
                null,
                Integer.parseInt(et_priority.text.toString()),
                0)
            personViewModel.insert(toDo = toDo)
        }
    }


}
