package com.nguyenhaidang_dangnh2406.moviekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nguyenhaidang_dangnh2406.moviekotlin.activitys.MasterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        main_login_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent = Intent(applicationContext,MasterActivity::class.java)
        startActivity(intent)
    }
}