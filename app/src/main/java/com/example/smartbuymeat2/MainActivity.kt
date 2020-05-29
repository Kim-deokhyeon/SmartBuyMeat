package com.example.smartbuymeat2

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import navigation.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.action_inform ->{
                var informFragment = InformFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,informFragment).commit()
                return true
            }
            R.id.action_nears ->{
                var nearsFragment = NearsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,nearsFragment).commit()
                return true
            }
            R.id.action_graph ->{
                var graphFragment = GraphFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,graphFragment).commit()
                return true
            }
            R.id.action_trade ->{
                var tradeFragment = TradeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,tradeFragment).commit()
                return true
            }
            R.id.action_camera ->{
                var dailyFragment = CameraFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,dailyFragment).commit()
                return true
            }
        }
        return false
    }
}
