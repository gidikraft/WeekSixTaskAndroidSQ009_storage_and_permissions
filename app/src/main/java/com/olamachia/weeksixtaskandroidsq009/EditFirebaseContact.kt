package com.olamachia.weeksixtaskandroidsq009

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityEditFirebaseContactBinding

class EditFirebaseContact : AppCompatActivity() {
    private lateinit var binding: ActivityEditFirebaseContactBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditFirebaseContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Contacts")

        binding.editContactBtn.setOnClickListener {
            editContact()
        }

    }

    private fun editContact(){
        val name = binding.etEditName.text.toString().trim()
        val phone = binding.etEditPhoneNumber.text.toString().trim()


        if (name.isNotEmpty() && phone.isNotEmpty()){
//            val users = MyModel(null, name, phone)
//            database.child(users.id!!).setValue(users)

            binding.etEditName.setText("")
            binding.etEditPhoneNumber.setText("")
            Toast.makeText(this, "Data edited", Toast.LENGTH_SHORT).show()

        } else{
            Toast.makeText(this, "Data not edited", Toast.LENGTH_SHORT).show()
        }
    }

}