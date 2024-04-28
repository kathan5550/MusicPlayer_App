package com.example.musicplayer_app.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.musicplayer_app.UserProfileActivity
import com.example.musicplayer_app.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern
var TAG="Signup_activity"
class SignUp_activity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAccount.setOnClickListener{

            val email = binding.edtEmailAddress.text.toString()
            val fullName= binding.edtFullname.text.toString()
            val username= binding.edtUsername.text.toString()
            val password= binding.edtPassword.text.toString()
            val confirmpassword= binding.edtConfirmPassword.text.toString()
            val inprogress= binding.progressbar



            if(!Pattern.matches(Patterns.PHONE.pattern(),username)){
                binding.edtUsername.setError("Length should be 12 char")
                return@setOnClickListener
            }

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email))
            {
                binding.edtEmailAddress.setError("Invalid Email")
                return@setOnClickListener
            }

            if(password.length <6){
                binding.edtPassword.setError("Length should be 6 char")
                return@setOnClickListener
            }

            if(!password.equals(confirmpassword))
            {
                binding.edtConfirmPassword.setError("Password not matched")
                return@setOnClickListener
            }


            createAccountWithFirebase(email,password,username)

        }
        binding.GoToMainScreen.setOnClickListener {
            startActivity(Intent(this@SignUp_activity,Sigin_activity::class.java))
        }

    }

        fun createAccountWithFirebase(email: String, password: String, fullName: String)
        {
            setupProgressbar(true)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {authResult->
                    setupProgressbar(false)
                    Toast.makeText(applicationContext,"User created sucessfully",Toast.LENGTH_LONG).show()
                    finish()
                }.addOnFailureListener {
                    setupProgressbar(false)
                    Toast.makeText(applicationContext,"Create account failed",Toast.LENGTH_LONG).show()
                }

            intent.putExtra("Full_name",fullName)
            startActivity(Intent(this@SignUp_activity, UserProfileActivity::class.java).apply {
                putExtra("FULL_NAME", fullName)
            })

        }

    private fun saveUserToDatabase(userId: String, username: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("users")


        databaseReference.child(userId).child("username").setValue(username)
            .addOnSuccessListener {

            }
            .addOnFailureListener {exception->
                Log.e(TAG, "Error saving username to database: ${exception.message}")
            }

    }


    fun setupProgressbar(inProgress:Boolean)
        {
            if (inProgress)
            {
                binding.btnCreateAccount.visibility=View.GONE
                binding.progressbar.visibility= View.VISIBLE
            }
            else
            {
                binding.btnCreateAccount.visibility=View.VISIBLE
                binding.progressbar.visibility= View.GONE
            }
        }
}