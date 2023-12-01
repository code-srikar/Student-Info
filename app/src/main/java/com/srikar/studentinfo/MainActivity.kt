package com.srikar.studentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.srikar.studentinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding1: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        binding1.add.setOnClickListener(){

            val intent = Intent(this,New::class.java)
            startActivity(intent)

        }

        binding1.update.setOnClickListener(){

            val intent = Intent(this,Update::class.java)
            startActivity(intent)

        }

        binding1.display.setOnClickListener(){

            val intent = Intent(this,Display::class.java)
            startActivity(intent)

        }

        binding1.delete.setOnClickListener(){

            val intent = Intent(this,Delete::class.java)
            startActivity(intent)

        }

    }
}