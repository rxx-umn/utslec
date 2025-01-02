package com.example.uts_lec

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*

class AddCaptionActivity : ComponentActivity() {

    private lateinit var capturedImageView: ImageView
    private lateinit var captionText: TextView
    private lateinit var cancelButton: ImageView
    private lateinit var sendButton: ImageView
    private lateinit var saveButton: ImageView
    private lateinit var progressBar: ProgressBar

    // Firebase Storage & Database
    private val storageRef = FirebaseStorage.getInstance().reference
    private val databaseRef = FirebaseDatabase.getInstance().getReference("posts")

    private lateinit var imageBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_caption)

        // Firebase Connection Log
        FirebaseDatabase.getInstance().getReference("posts")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("FirebaseConnection", "Connected to Firebase: Data Snapshot = ${dataSnapshot.value}")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseConnection", "Failed to connect to Firebase: ${error.message}")
                }
            })

        // Initialize views
        captionText = findViewById(R.id.captionText)
        capturedImageView = findViewById(R.id.capturedImageView)
        progressBar = findViewById(R.id.progressBar)
        cancelButton = findViewById(R.id.cancelButton)
        sendButton = findViewById(R.id.sendButton)
        saveButton = findViewById(R.id.saveButton)

        // Capture Image Path
        val imagePath = intent.getStringExtra("capturedImagePath") ?: ""
        Log.d("AddCaptionActivity", "imagePath: $imagePath")
        if (imagePath.isNotEmpty()) {
            val imageFile = File(imagePath)
            imageBitmap = BitmapFactory.decodeFile(imageFile.absolutePath) // Decode file to Bitmap
            capturedImageView.setImageBitmap(imageBitmap)
        }

        // Cancel Button
        cancelButton.setOnClickListener {
            finish() // Return to previous activity
        }

        // Save Button (Save Image to Gallery)
        saveButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                saveImageToGallery(imageBitmap)
                Toast.makeText(this, "Image saved to gallery!", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    200
                )
            }
        }

        // Send Button (Upload Image to Firebase)
        sendButton.setOnClickListener {
            val caption = captionText.text.toString().trim()
            if (caption.isNotEmpty()) {
                Log.d("AddCaptionActivity", "Caption: $caption")
                uploadImageToFirebase(imageBitmap, caption)
            } else {
                Toast.makeText(this, "Please add a caption first!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Upload Image to Firebase
    private fun uploadImageToFirebase(image: Bitmap, caption: String) {
        // Show progress bar when upload starts
        progressBar.visibility = View.VISIBLE

        // Convert Bitmap to byte array
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()

        // Create a unique image name
        val imageName = UUID.randomUUID().toString()
        val imageRef = storageRef.child("post_images/$imageName.jpg")

        // Upload image to Firebase Storage
        val uploadTask = imageRef.putBytes(imageData)
        uploadTask.addOnSuccessListener {
            // Get download URL of uploaded image
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Log.d("AddCaptionActivity", "Image URL retrieved successfully: $uri")
                saveDataToDatabase(uri.toString(), caption) // Save data to database
            }.addOnFailureListener { exception ->
                progressBar.visibility = View.GONE // Hide progress bar if failed
                Log.e("AddCaptionActivity", "Failed to get download URL: ${exception.message}")
                Toast.makeText(this, "Failed to get image URL: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            progressBar.visibility = View.GONE // Hide progress bar if failed
            Log.e("AddCaptionActivity", "Failed to upload image: ${exception.message}")
            Toast.makeText(this, "Failed to upload image: ${exception.message}", Toast.LENGTH_SHORT).show()
        }.addOnCompleteListener {
            Log.d("AddCaptionActivity", "Upload complete")
        }
    }

    // Save data to Firebase Database
    private fun saveDataToDatabase(imageUrl: String, caption: String) {
        val dataMap = hashMapOf(
            "imageUrl" to imageUrl,
            "caption" to caption
        )

        val newPostRef = databaseRef.push() // Add log to check if this is successful
        newPostRef.setValue(dataMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("AddCaptionActivity", "Data saved successfully to Firebase Realtime Database: $imageUrl")
                Toast.makeText(this, "Image and caption uploaded successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to MenuActivity after successful upload
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("imageUrl", imageUrl)
                intent.putExtra("caption", caption)
                startActivity(intent)
                finish()
            } else {
                Log.e("AddCaptionActivity", "Failed to save data to Firebase: ${task.exception?.message}")
                Toast.makeText(this, "Failed to save data to database: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            Log.e("AddCaptionActivity", "Error saving data: ${exception.message}")
            Toast.makeText(this, "Failed to save data: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Save Image to Gallery
    private fun saveImageToGallery(image: Bitmap) {
        // Create a ContentValues object to store image metadata
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "captured_image_${UUID.randomUUID()}")
            put(MediaStore.Images.Media.DESCRIPTION, "Captured Image")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        // Insert the content values into MediaStore and get the URI
        val imageUri = this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        // Check if the imageUri is not null before proceeding
        imageUri?.let { uri ->
            try {
                // Open an output stream for the image URI
                val outputStream = contentResolver.openOutputStream(uri)

                // Check if outputStream is not null
                outputStream?.let { stream ->
                    // Compress and save the bitmap to the output stream
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()
                    Log.d("AddCaptionActivity", "Image saved to gallery successfully")
                }
            } catch (e: IOException) {
                Log.e("AddCaptionActivity", "Error saving image to gallery: ${e.message}")
            }
        }
    }
}