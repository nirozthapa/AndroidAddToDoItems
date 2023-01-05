package com.example.recyclerviewtestapplication

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recyclerviewtestapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var text: String
    lateinit var recyclerArray: ArrayList<String>
    lateinit var adapter: RecyclerViewAdapter
    var filteredArray = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerArray = ArrayList()
        setContentView(binding.root)
        initViews()
        setUpRecyclerView()
    }

    private fun initViews() {

        binding.btnAddList.setOnClickListener() {
            if (binding.edText.text.toString() != null || binding.edText.text.toString() != "") {
                recyclerArray.add(binding.edText.text.toString())
                setUpAdapter(binding.edText.text.toString(), this.recyclerArray, this)
            }
        }

        binding.btnDeleteList.setOnClickListener() {
            adapter.filterRecyclerViewData(binding.edText.text.toString())
            recyclerArray.filterTo(filteredArray, { it != binding.edText.text.toString() })
            recyclerArray = filteredArray
            filteredArray = arrayListOf()
            UpdateRecyclerView()

        }

    }

    private fun setUpRecyclerView() {
        //define layout manager and assign the manager to the recycler view
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun UpdateRecyclerView() {
        adapter.setNewData(this.recyclerArray)
        adapter.notifyDataSetChanged()

    }

    private fun setUpAdapter(textToRemove: String, data: ArrayList<String>, context: Context) {
        adapter = RecyclerViewAdapter(textToRemove, data, context)
        binding.recyclerView.adapter = adapter
    }


}