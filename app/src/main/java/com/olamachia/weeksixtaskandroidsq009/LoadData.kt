package com.olamachia.weeksixtaskandroidsq009

import android.Manifest
import android.content.Intent
import android.content.Intent.createChooser
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityLoadDataBinding

class LoadData : AppCompatActivity(), RecyclerViewItemListener {
    private lateinit var binding: ActivityLoadDataBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    val PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()               //create an instance of the database
        reference = database.getReference("Contacts")      //set variable to refer to the instance of the database created
        loadData()

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

    //override onCallButtonClicked that is declared in itemViewListener
    override fun onCallButtonClicked(model: MyModel) {
        if(ContextCompat.checkSelfPermission(this@LoadData, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@LoadData, arrayOf(Manifest.permission.CALL_PHONE),PERMISSION_CODE)
        } else {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${model.phone}")
            startActivity(intent)
//            startActivity(intent, null)
        }
        Toast.makeText(this, "${model.phone}", Toast.LENGTH_SHORT).show()
    }
    //override onEditButtonClicked that is declared in itemViewListener
    override fun onEditButtonClicked(model: MyModel) {
        val pageIntent = Intent(this, EditFirebaseContact::class.java)
        startActivity(pageIntent)
    }
    //override onDeleteButtonClicked that is declared in itemViewListener
    override fun onDeleteButtonClicked(model: MyModel) {
        reference.child(model.id!!).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Contact deleted successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Contact delete unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }
    //override onShareButtonClicked that is declared in itemViewListener
    override fun onShareButtonClicked(model: MyModel) {
        Toast.makeText(this, "Share button clicked", Toast.LENGTH_SHORT).show()
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "type/plain"
//        intent.putExtra(Intent.EXTRA_TEXT, "${model.name} \n ${model.phone}")

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, "${model.name} \n ${model.phone}")
        intent.type  = "text/plain"
        startActivity(
            createChooser(
                intent,
                model.name
            )
        )
    }
}