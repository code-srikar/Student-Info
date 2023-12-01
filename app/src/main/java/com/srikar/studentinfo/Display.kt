package com.srikar.studentinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.srikar.studentinfo.databinding.ActivityDisplayBinding

class Display : AppCompatActivity() {

    private lateinit var binding3:ActivityDisplayBinding
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding3.root)

        database = Firebase.database.reference

        binding3.searchBtn.setOnClickListener(){

            binding3.resultCard.visibility = View.VISIBLE

            if(!TextUtils.isEmpty(binding3.sRno.text)) {

                database.child(binding3.sRno.text.toString().trim()).get().addOnSuccessListener {
                    binding3.name.text = "Name : " + it.child("name").value.toString()
                    binding3.total.text = "Total : " + it.child("total").value.toString()
                    binding3.avg.text = "Average : " + it.child("average").value.toString()
                }

                binding3.sRno.text.clear()

                val scale = AnimationUtils.loadAnimation(this, R.anim.scale)
                binding3.resultCard.startAnimation(scale)

            }
            else{
                Toast("Enter Roll Number",R.drawable.baseline_error_24)
            }

        }

    }

    private fun Toast(msg:String, img: Int) {

        val toast = Toast(this)
        val view = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.viewContainer))
        toast.view = view
        val txtMsg = view.findViewById<TextView>(R.id.msg)
        txtMsg.text = msg
        val imgSrc = view.findViewById<ImageView>(R.id.img)
        imgSrc.setImageResource(img)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()

    }
}