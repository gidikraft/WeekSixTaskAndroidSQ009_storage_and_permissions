package com.olamachia.weeksixtaskandroidsq009

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(var list: ArrayList<MyModel>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name = itemView.findViewById<TextView>(R.id.contact_name)
        var phone = itemView.findViewById<TextView>(R.id.contact_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.firebase_contact_recyclerview, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.phone.text = list[position].phone
    }

    override fun getItemCount(): Int {
        return list.size
    }
}