package com.olamachia.weeksixtaskandroidsq009

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.weeksixtaskandroidsq009.databinding.ActivityMainBinding

private const val PERMISSION_READ_CONTACT = 10
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactView: RecyclerView
    private val phoneContactList : MutableList<Model> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readContactBtn.setOnClickListener {
            requestContactPermission()
        }

        binding.addContactBtn.setOnClickListener {
            val pageIntent = Intent(this, FirebaseContact::class.java)
            startActivity(pageIntent)
        }
    }

    private fun readContact(){
        if (phoneContactList.isEmpty()){
            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
            var count = 0

            while (contacts!!.moveToNext() && count < 50){              //set the number of contacts to display to 50
                val name = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val objects = Model(name, number)       //list of contacts that will be displayed
                phoneContactList.add(objects)
                count++
            }
            contacts.close()        //close contact after reading
        }
        contactView = binding.contactList      //initiate recyclerView in the activity_main layout
        val myContactAdapter = ContactRecyclerView(phoneContactList, this)       //initiate ContactAdapter to hold the contactList in this application context

        contactView.layoutManager = LinearLayoutManager(this)       //organize in LinearLayout
        contactView.adapter = myContactAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_READ_CONTACT){
            if (grantResults.size ==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permissions granted", Toast.LENGTH_SHORT).show()
                requestContactPermission()
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestContactPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==PackageManager.PERMISSION_GRANTED){
            readContact()
        } else{
            Toast.makeText(this, "Grant permissions from phone settings", Toast.LENGTH_LONG).show()
            requestPermissionsCompat(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_READ_CONTACT)
        }
    }

    private fun requestPermissionsCompat(permissionsArray: Array<String>, requestCode: Int){
        ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
    }
}