package com.srikar.studentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srikar.studentinfo.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener(){

            val intent = Intent(this,Update1::class.java)
            intent.putExtra("rno",binding.sRno.text.toString().trim())
            startActivity(intent)
            binding.sRno.text.clear()
            finish()

        }

    }
}