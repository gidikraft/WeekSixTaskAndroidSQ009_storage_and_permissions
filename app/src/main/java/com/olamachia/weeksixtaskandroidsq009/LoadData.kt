package com.olamachia.weeksixtaskandroidsq009

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityLoadDataBinding

class LoadData : AppCompatActivity() {
    private lateinit var binding: ActivityLoadDataBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Contacts")           //create an instance of the database
        loadData()
        //add onClickListener to update button
        binding.updateContactBtn.setOnClickListener {
//            updateContact()
        }

    }

    private fun loadData(){
        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<MyModel>()
                for (data in snapshot.children){
                    val model = data.getValue(MyModel::class.java)
                    list.add(model as MyModel)
                }
                if (list.size > 0){
                    val adapter = FirebaseDataAdapter(list)
                    binding.recyclerviewLayout.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", error.toString())
            }

        })
    }

}