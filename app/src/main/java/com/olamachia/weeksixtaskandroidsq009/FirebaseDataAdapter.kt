package com.olamachia.weeksixtaskandroidsq009

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FirebaseDataAdapter(var list: ArrayList<MyModel>): RecyclerView.Adapter<FirebaseDataAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name: TextView = itemView.findViewById(R.id.contact_name)
        var phone: TextView = itemView.findViewById(R.id.contact_number)
        var callButton: ImageView = itemView.findViewById(R.id.call_icon)
        val editButton: ImageView = itemView.findViewById(R.id.edit_icon)
        val shareButton: ImageView = itemView.findViewById(R.id.share_icon)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_icon)

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

    fun onDelete(){

    }
}