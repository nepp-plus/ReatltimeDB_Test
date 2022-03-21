package com.neppplus.reatltimedb_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.reatltimedb_test.R
import com.neppplus.reatltimedb_test.datas.MessageData

class MessageRecyclerAdapter(
    val mContext: Context,
    val mList: List<MessageData>
) : RecyclerView.Adapter<MessageRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtMessage = view.findViewById<TextView>(R.id.txtMessage)

        fun bind(data: MessageData) {

            txtMessage.text = data.content

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.message_list_item, parent, false)
        return  MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size

}