package com.example.uts_lec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfilePageActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")

        loadUserData() // Memuat data pengguna saat activity dimulai

        // Tambahkan listener untuk Edit Profile
        val editProfileTextView: TextView = findViewById(R.id.editProfile)
        editProfileTextView.setOnClickListener {
            // Arahkan ke EditAccountActivity
            startActivity(Intent(this, EditAccountActivity::class.java))
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userRef = databaseRef.child(userId)
            userRef.get().addOnSuccessListener { dataSnapshot ->
                val user = dataSnapshot.getValue(User::class.java)
                if (user != null) {
                    // Update UI dengan data pengguna
                    val nameTextView: TextView = findViewById(R.id.nameTextView)
                    val emailTextView: TextView = findViewById(R.id.emailTextView)
                    val birthdayTextView: TextView = findViewById(R.id.birthdayTextView)

                    nameTextView.text = user.name
                    emailTextView.text = user.email
                    birthdayTextView.text = user.birthday
                } else {
                    Log.e("ProfilePageActivity", "User data is null")
                }
            }.addOnFailureListener { exception ->
                Log.e("ProfilePageActivity", "Error loading user data: ${exception.message}")
                Toast.makeText(this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }
}
