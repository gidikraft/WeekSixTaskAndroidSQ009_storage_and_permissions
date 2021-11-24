package com.olamachia.weeksixtaskandroidsq009

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRecyclerView(items: List<Model>, context: Context): RecyclerView.Adapter<ContactRecyclerView.MyViewHolder>(){
    private var list: List<Model> = items
    private var context: Context = context

    //    set viewHolder to hold items from Model Class
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model){
            itemView.findViewById<TextView>(R.id.tv_name).text = model.name
            itemView.findViewById<TextView>(R.id.tv_number).text = model.number
        }
    }

    //  create RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_recyclerview, parent, false))
    }

    //  bind recyclerView View to ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //  get size of item to bind
    override fun getItemCount(): Int {
        return list.size
    }
}