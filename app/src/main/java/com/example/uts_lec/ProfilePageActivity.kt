package com.example.uts_lec

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProfilePageActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        profileImage = findViewById(R.id.profileImage)
        val editProfile: TextView = findViewById(R.id.editProfile)
        val friendsButton: Button = findViewById(R.id.friendsButton)
        val settingsButton: Button = findViewById(R.id.settingsButton)

        // Set click listener for profile image
        profileImage.setOnClickListener {
            openGallery()
        }

        // Set click listener for Edit Profile
        editProfile.setOnClickListener {
            val intent = Intent(this, EditAccountActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Friends
        friendsButton.setOnClickListener {
            val intent = Intent(this, AddFriendActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Settings
        settingsButton.setOnClickListener {
            val intent = Intent(this, AccountSettingsActivity::class.java)
            startActivity(intent)
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