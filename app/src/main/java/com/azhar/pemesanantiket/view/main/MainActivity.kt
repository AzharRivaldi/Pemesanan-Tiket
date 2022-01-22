package com.azhar.pemesanantiket.view.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.pemesanantiket.R
import com.azhar.pemesanantiket.view.history.HistoryActivity
import com.azhar.pemesanantiket.view.input.DataKapalActivity
import com.azhar.pemesanantiket.view.input.DataKeretaActivity
import com.azhar.pemesanantiket.view.input.DataPesawatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBar()

        imageProfile.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        cvPesawat.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataPesawatActivity::class.java)
            startActivity(intent)
        }

        cvKapal.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataKapalActivity::class.java)
            startActivity(intent)
        }

        cvKereta.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataKeretaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}