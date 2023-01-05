package com.example.recyclerviewtestapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtestapplication.databinding.RecyclerviewCellBinding
import kotlin.math.ceil

class RecyclerViewAdapter(txtToRemove: String, data:ArrayList<String>, context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var data: ArrayList<String>
    var context: Context
    var txtToRemove: String
    var filteredArray = arrayListOf<String>()


    init {
        this.context = context
        this.data = data
        this.txtToRemove = txtToRemove
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //OnCreate view holder defines the cell and return the cell to parent class
        var cell = RecyclerviewCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(data[position],context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setNewData(newData: ArrayList<String>) {
        data = newData
    }

     fun filterRecyclerViewData(txt: String) {
        data.filterTo(filteredArray, { it != txt})
        data = filteredArray
        filteredArray = arrayListOf()
        notifyDataSetChanged()
    }


    //view holder defines the view that need to be hold by the recycler view and it needs to
    //import the root
    class ViewHolder(var cell: RecyclerviewCellBinding) : RecyclerView.ViewHolder(cell.root) {
        fun bindView(s: String, context: Context) {
            cell.cellRecyclerView.text = s
        }
    }
}