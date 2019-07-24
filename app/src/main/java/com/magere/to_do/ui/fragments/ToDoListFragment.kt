package com.magere.to_do.ui.fragments


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magere.to_do.adapters.ItemTouchListener
import com.magere.to_do.adapters.ToDoListAdapter
import com.magere.to_do.data.db.ToDoEntity
import com.magere.to_do.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.INPUT_SERVICE
import androidx.core.content.ContextCompat.getSystemService




class ToDoListFragment : Fragment() {
    private lateinit var personViewModel: ToDoViewModel
    private var persons = mutableListOf<ToDoEntity>()
    private lateinit var mTouchListener: ItemTouchListener
    private lateinit var mAdapter: ToDoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.magere.to_do.R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personViewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        mTouchListener = object : ItemTouchListener {
            override fun onRightMenuClick(id: Int?) {
                personViewModel.delete(id)
            }

            override fun onItemClick(position: Int) {
                Toast.makeText(view.context, "$position", Toast.LENGTH_LONG).show()
            }
        }
        setupAdapter()

        personViewModel.getAllPersons()?.observe(this, Observer {
            mAdapter.setData(it)
        })

        et_quick_task.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p2 > 0 || p3 > 0 || p1 > 0) {
                    tv_add_quick_task.visibility = VISIBLE
                } else {
                    tv_add_quick_task.visibility = GONE
                }
            }
        })

        tv_add_quick_task.setOnClickListener {
            personViewModel.insert(
                ToDoEntity(
                    title = et_quick_task.text.toString(), id = null
                )
            )
            val focus = activity?.currentFocus
            if (focus != null) {
                val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focus.applicationWindowToken, 0)
            }
            et_quick_task.text.clear()
            Toast.makeText(view.context, "Task added", Toast.LENGTH_SHORT).show()
        }

        addToDo.setOnClickListener(Navigation.createNavigateOnClickListener(com.magere.to_do.R.id.addToDoFragment))
    }

    private fun setupAdapter() {
        mAdapter = ToDoListAdapter(mTouchListener)
        val layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
        rv.layoutManager = layoutManager
        rv.adapter = mAdapter
        mAdapter.setData(persons)
    }
}
