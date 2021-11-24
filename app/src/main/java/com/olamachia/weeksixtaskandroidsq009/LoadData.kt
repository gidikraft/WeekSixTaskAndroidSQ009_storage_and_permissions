package com.olamachia.weeksixtaskandroidsq009

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityLoadDataBinding

class LoadData : AppCompatActivity(), RecyclerViewItemListener {
    private lateinit var binding: ActivityLoadDataBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()               //create an instance of the database
        reference = database.getReference("Contacts")      //set variable to refer to the instance of the database created
        loadData()
        //add onClickListener to update button
        binding.updateContactBtn.setOnClickListener {
//            updateContact()
        }

    }
    //this function loads data from the firebase database into the recyclerview adapter that display the data
    private fun loadData(){
        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<MyModel>()
                for (data in snapshot.children){
                    val model = data.getValue(MyModel::class.java)
                    list.add(model as MyModel)
                }
                if (list.size > 0){
                    val adapter = FirebaseDataAdapter(list, this@LoadData)
                    binding.recyclerviewLayout.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", error.toString())
            }

        })
    }

    override fun onItemClicked(model: MyModel) {
        Toast.makeText(this, model.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCallButtonClicked(model: MyModel) {
        Toast.makeText(this, "${model.phone}", Toast.LENGTH_SHORT).show()
    }

    override fun onEditButtonClicked(model: MyModel) {
        TODO("Not yet implemented")
    }

    override fun onDeleteButtonClicked(model: MyModel) {
        TODO("Not yet implemented")
    }

    override fun onShareButtonClicked(model: MyModel) {
        TODO("Not yet implemented")
    }

}