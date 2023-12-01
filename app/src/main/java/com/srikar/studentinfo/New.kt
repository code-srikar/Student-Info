package com.srikar.studentinfo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.srikar.studentinfo.databinding.ActivityNewBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.properties.Delegates

class New : AppCompatActivity() {

    private lateinit var binding2: ActivityNewBinding
    private lateinit var database: DatabaseReference

    private lateinit var sName:String
    private lateinit var sRno:String
    private var s1 by Delegates.notNull<Float>()
    private var s2 by Delegates.notNull<Float>()
    private var s3 by Delegates.notNull<Float>()
    private var s4 by Delegates.notNull<Float>()
    private var s5 by Delegates.notNull<Float>()
    private var tot by Delegates.notNull<Float>()
    private var avg by Delegates.notNull<Float>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        database = Firebase.database.reference

        binding2.enter.setOnClickListener() {
            if (!TextUtils.isEmpty(binding2.name.text) && !TextUtils.isEmpty(binding2.rno.text)) {
                if(!TextUtils.isEmpty(binding2.s1.text)&& !TextUtils.isEmpty(binding2.s2.text)&& !TextUtils.isEmpty(binding2.s3.text)&& !TextUtils.isEmpty(binding2.s4.text)&& !TextUtils.isEmpty(binding2.s5.text)) {

                    sName = binding2.name.text.toString().trim()
                    sRno = binding2.rno.text.toString().trim()
                    s1 = binding2.s1.text.toString().trim().toFloat()
                    s2 = binding2.s2.text.toString().trim().toFloat()
                    s3 = binding2.s3.text.toString().trim().toFloat()
                    s4 = binding2.s4.text.toString().trim().toFloat()
                    s5 = binding2.s5.text.toString().trim().toFloat()
                    tot = s1 + s2 + s3 + s4 + s5
                    avg = tot / 5

                    if (s1 in 0.0..100.0 && s2 in 0.0..100.0 && s3 in 0.0..100.0 && s4 in 0.0..100.0 && s5 in 0.0..100.0) {

                        saveData()

                        Toast("Entered Successfully", R.drawable.baseline_check_circle_24)

                        val intentR = Intent(this, MainActivity::class.java)
                        startActivity(intentR)
                        finish()

                        binding2.name.text.clear()
                        binding2.rno.text.clear()
                        binding2.s1.text.clear()
                        binding2.s2.text.clear()
                        binding2.s3.text.clear()
                        binding2.s4.text.clear()
                        binding2.s5.text.clear()

                    } else {
                        Toast("Invalid Marks", R.drawable.baseline_do_not_disturb_alt_24)
                    }
                }
                else{
                    Toast("Enter All Marks",R.drawable.baseline_error_24)
                }
            }
            else{
                Toast("Details Required",R.drawable.baseline_error_24)
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

    private fun saveData() {

        val student = StudentData(sName,sRno,s1,s2,s3,s4,s5,tot,avg)
        database.child(sRno).setValue(student)

    }
}