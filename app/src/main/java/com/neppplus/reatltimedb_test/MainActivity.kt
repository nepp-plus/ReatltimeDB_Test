package com.neppplus.reatltimedb_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.*
import com.neppplus.reatltimedb_test.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var childCount = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        db.getReference("message").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                childCount = snapshot.childrenCount

                Log.d("childCount", childCount.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        binding.btnSend.setOnClickListener {


            val inputContent = binding.edtMessage.text.toString()
            db.getReference("message").child((childCount).toString()).child("content").setValue(inputContent)

        }

    }

    override fun setValues() {





    }
}