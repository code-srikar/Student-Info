package com.srikar.studentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.srikar.studentinfo.databinding.ActivityDeleteBinding

class Delete : AppCompatActivity() {

    private lateinit var binding4: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    private lateinit var rootRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding4 = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding4.root)

        database = Firebase.database.reference
        rootRef = FirebaseDatabase.getInstance().getReference()

        binding4.deleteBtn.setOnClickListener(){

            if(!TextUtils.isEmpty(binding4.sRno.text)) {

                database.child(binding4.sRno.text.toString()).removeValue().addOnSuccessListener {
                    Toast("Deleted Successfully",R.drawable.baseline_check_circle_24)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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