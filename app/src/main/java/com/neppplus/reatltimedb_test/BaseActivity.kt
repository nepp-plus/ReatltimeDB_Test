package com.neppplus.reatltimedb_test

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase
import com.neppplus.reatltimedb_test.api.APIList
import com.neppplus.reatltimedb_test.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

//    모든 화면에서, apiList 변수가 있다면 => apiList.서버기능( ) 형태로 간단히 코딩 가능.
    lateinit var apiList: APIList
    val db = FirebaseDatabase.getInstance("https://retrofitlibrarytest-20211122-default-rtdb.asia-southeast1.firebasedatabase.app")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create( APIList::class.java )

        supportActionBar?.let {
            setCustomActionBar()
        }

    }

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar() {

//        val defaultActionBar = supportActionBar!!
//
//        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//
////        defaultActionBar.setDisplayShowCustomEnabled(true)
//
//        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)
//
//        val toolBar = defaultActionBar.customView.parent as Toolbar
//        toolBar.setContentInsetsAbsolute(0, 0)

//        UI 요소들 실제 값 대입

    }

}