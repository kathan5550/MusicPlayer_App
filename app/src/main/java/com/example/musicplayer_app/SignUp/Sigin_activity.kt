package com.example.musicplayer_app.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.musicplayer_app.MadeForYou_Activity
import com.example.musicplayer_app.MainActivity
import com.example.musicplayer_app.R
import com.example.musicplayer_app.databinding.ActivitySiginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class Sigin_activity : AppCompatActivity() {

    lateinit var binding: ActivitySiginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{

            val email = binding.siginEmail.text.toString()
            val password= binding.siginPassword.text.toString()

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email))
            {
                binding.siginEmail.setError("Invalid Email")
                return@setOnClickListener
            }

            if(password.length <6){
                binding.siginPassword.setError("Length should be 6 char")
                return@setOnClickListener
            }

            LoginwithFirebase(email,password)

        }

        binding.GoToSignup.setOnClickListener {
            startActivity(Intent(this@Sigin_activity,SignUp_activity::class.java))
        }


    }

    fun LoginwithFirebase(email:String,password:String)
    {
        setupProgressbar(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                setupProgressbar(false)
                //Toast.makeText(applicationContext,"User Login sucessfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@Sigin_activity,MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                setupProgressbar(false)
                Toast.makeText(applicationContext,"User Login failed", Toast.LENGTH_LONG).show()
            }

    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@Sigin_activity,MainActivity::class.java))
            finish()
        }
    }

    fun setupProgressbar(inProgress:Boolean)
    {
        if (inProgress)
        {
            binding.btnLogin.visibility= View.GONE
            binding.progressbar.visibility= View.VISIBLE
        }
        else
        {
            binding.btnLogin.visibility= View.VISIBLE
            binding.progressbar.visibility= View.GONE
        }
    }
}