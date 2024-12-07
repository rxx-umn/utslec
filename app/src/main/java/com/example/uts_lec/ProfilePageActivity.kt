package com.example.uts_lec

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfilePageActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var usernameTextView: TextView
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        profileImage = findViewById(R.id.profileImage)
        usernameTextView = findViewById(R.id.username)
        val editProfile: TextView = findViewById(R.id.editProfile)
        val friendsButton: Button = findViewById(R.id.friendsButton)
        val settingsButton: Button = findViewById(R.id.settingsButton)
        val backButton: ImageView = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")

        loadUserData()

        profileImage.setOnClickListener {
            openGallery()
        }

        editProfile.setOnClickListener {
            startActivity(Intent(this, EditAccountActivity::class.java))
        }

        friendsButton.setOnClickListener {
            startActivity(Intent(this, AddFriendActivity::class.java))
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, AccountSettingsActivity::class.java))
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        usernameTextView.text = it.name // Menampilkan nama pengguna dari Firebase
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            profileImage.setImageURI(imageUri)

            // Update image in MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("imageUri", imageUri.toString())
            startActivity(intent)
        }
    }
}