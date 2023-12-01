package com.srikar.studentinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.srikar.studentinfo.databinding.ActivityUpdate1Binding

class Update1 : AppCompatActivity() {

    private lateinit var binding:ActivityUpdate1Binding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdate1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference

        val sRno = intent.getStringExtra("rno").toString()

        database.child(sRno).get().addOnSuccessListener {

            binding.name.setText(it.child("name").value.toString())
            binding.s1.setText(it.child("sub1").value.toString())
            binding.s2.setText(it.child("sub2").value.toString())
            binding.s3.setText(it.child("sub3").value.toString())
            binding.s4.setText(it.child("sub4").value.toString())
            binding.s5.setText(it.child("sub5").value.toString())

        }

        binding.update.setOnClickListener(){

            val name = binding.name.text.toString()
            val s1 = binding.s1.text.toString().toFloat()
            val s2 = binding.s2.text.toString().toFloat()
            val s3 = binding.s3.text.toString().toFloat()
            val s4 = binding.s4.text.toString().toFloat()
            val s5 = binding.s5.text.toString().toFloat()
            val tot = s1 + s2 + s3 + s4 + s5
            val avg = tot / 5

            val editMap = mapOf(
                "name" to name,
                "sub1" to s1,
                "sub2" to s2,
                "sub3" to s3,
                "sub4" to s4,
                "sub5" to s5,
                "total" to tot,
                "average" to avg
            )

            database.child(sRno).updateChildren(editMap)

            Toast("Updated Successfully",R.drawable.baseline_check_circle_24)
            finish()

        }

    }

    private fun Toast(msg:String, img: Int) {

        val toast = android.widget.Toast(this)
        val view = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.viewContainer))
        toast.view = view
        val txtMsg = view.findViewById<TextView>(R.id.msg)
        txtMsg.text = msg
        val imgSrc = view.findViewById<ImageView>(R.id.img)
        imgSrc.setImageResource(img)
        toast.duration = android.widget.Toast.LENGTH_SHORT
        toast.show()

    }
}