package com.example.cropdigital

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBack()
    }

    private fun setupBack(){
        supportFragmentManager.addOnBackStackChangedListener {
            val stackHeight = supportFragmentManager.backStackEntryCount
            if (stackHeight > 0) {
                supportActionBar!!.setHomeButtonEnabled(true)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.setHomeButtonEnabled(false)
            }
        }
    }
}
