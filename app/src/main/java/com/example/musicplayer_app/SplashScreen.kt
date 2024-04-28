package com.example.musicplayer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.musicplayer_app.SignUp.HomePage_Activity
import com.example.musicplayer_app.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {


    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val animation: Animation = AnimationUtils.loadAnimation(this,R.anim.splash_anim)
        binding.imageView.startAnimation(animation)
        binding.textView.startAnimation(animation)

        val splashDuration:Long=2000
        binding.root.postDelayed({
            startActivity(Intent(this,HomePage_Activity::class.java))
            finish()
        },splashDuration)
    }
}