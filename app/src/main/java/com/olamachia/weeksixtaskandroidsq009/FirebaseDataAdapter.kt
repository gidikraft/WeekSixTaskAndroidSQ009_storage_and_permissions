package com.olamachia.weeksixtaskandroidsq009

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FirebaseDataAdapter(var list: ArrayList<MyModel>, private val recyclerViewItemListener: RecyclerViewItemListener): RecyclerView.Adapter<FirebaseDataAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(model: MyModel){
            itemView.findViewById<TextView>(R.id.contact_name).text = model.name
            itemView.findViewById<TextView>(R.id.contact_number).text = model.phone
        }
        val callButton: ImageView = itemView.findViewById(R.id.call_icon)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_icon)
        val shareButton: ImageView = itemView.findViewById(R.id.share_icon)
        val editButton: ImageView = itemView.findViewById(R.id.edit_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.firebase_contact_recyclerview, parent, false))
    }
    //function that binds view to each layout component
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
        //set onClickListener to each recyclerview item
        holder.itemView.setOnClickListener {
            recyclerViewItemListener.onItemClicked(list[position])
        }
        //set onClickListener on call icon on each recyclerview item
        holder.callButton.setOnClickListener {
            recyclerViewItemListener.onCallButtonClicked(list[position])
        }
        //set onClickListener on delete icon on each recyclerview item
        holder.deleteButton.setOnClickListener {
            recyclerViewItemListener.onDeleteButtonClicked(list[position])
        }
        //set onClickListener on share icon on each recyclerview item
        holder.shareButton.setOnClickListener {
            recyclerViewItemListener.onShareButtonClicked(list[position])
        }
        //set onClickListener on edit icon on each recyclerview item
        holder.editButton.setOnClickListener {
            recyclerViewItemListener.onEditButtonClicked(list[position])
        }
    }
    //get the size of items to insert into recyclerview
    override fun getItemCount(): Int {
        return list.size
    }
}