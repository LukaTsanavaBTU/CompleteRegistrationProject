package com.example.completeregistrationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.completeregistrationproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btRegister.setOnClickListener {
            if(binding.etPassword.text.toString().length >= 6 &&
                binding.etEmail.text.toString().isValidEmail()){
                auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "წარმატებით შეიქმნა!", Toast.LENGTH_SHORT).show()
                            binding.etEmail.text.clear()
                            binding.etPassword.text.clear()
                        } else { Toast.makeText(applicationContext, "ვერ შეიქმნა", Toast.LENGTH_SHORT).show() }
                    }
            }
            else{ Toast.makeText(applicationContext, "სწორად შეიყვანეთ მონაცემები", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.
    matcher(this).matches()
}