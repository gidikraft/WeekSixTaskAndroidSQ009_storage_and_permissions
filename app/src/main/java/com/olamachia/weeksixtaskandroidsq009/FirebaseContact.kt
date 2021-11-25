package com.olamachia.weeksixtaskandroidsq009

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityFirebaseContactBinding

class FirebaseContact : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseContactBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //create an instance of the firebase database
        database = FirebaseDatabase.getInstance().getReference("Contacts")
        //add onClickListener to add contact button
        binding.addContactBtn.setOnClickListener {
            sendData()
        }
        //add onClickListener to button that loads data from the firebase
        binding.loadContactBtn.setOnClickListener {
            startActivity(Intent(applicationContext, LoadData::class.java))
        }
    }
    //this method sends your firebase instance to the firebase database
    private fun sendData(){
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhoneNumber.text.toString().trim()

        if (name.isNotEmpty() && phone.isNotEmpty()){
            val users = MyModel(null, name, phone)
            users.id = database.push().key
            database.child(users.id!!).setValue(users)

            binding.etName.setText("")
            binding.etPhoneNumber.setText("")
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()

        } else{
            Toast.makeText(this, "Data not sent", Toast.LENGTH_SHORT).show()
        }
    }
}