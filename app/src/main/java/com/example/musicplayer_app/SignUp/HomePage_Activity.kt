package com.example.musicplayer_app.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayer_app.R
import com.example.musicplayer_app.databinding.ActivityHomePageBinding

class HomePage_Activity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnSignup.setOnClickListener{
           val intent =Intent(this,SignUp_activity::class.java)
           startActivity(intent)
       }

        binding.btnSignin.setOnClickListener{
            val intent =Intent(this,Sigin_activity::class.java)
            startActivity(intent)
        }

    }
}