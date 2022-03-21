package com.neppplus.reatltimedb_test

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.neppplus.reatltimedb_test.adapters.MessageRecyclerAdapter
import com.neppplus.reatltimedb_test.databinding.ActivityMainBinding
import com.neppplus.reatltimedb_test.datas.MessageData

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var childCount = 0L

    val messageList = ArrayList<MessageData>()
    lateinit var mAdapter: MessageRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        db.getReference("message").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                if (childCount == 0L) {

                    for (data in  snapshot.children) {
                        messageList.add( MessageData(data.child("content").value.toString()) )
                    }
                }
                else {
                    messageList.add( MessageData(snapshot.children.last().child("content").value.toString()) )
                }
                mAdapter.notifyDataSetChanged()


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

        mAdapter = MessageRecyclerAdapter(mContext, messageList)
        binding.mainRecyclerView.adapter = mAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)


    }
}