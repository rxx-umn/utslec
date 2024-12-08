package com.example.uts_lec

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.File

class MenuActivity : ComponentActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetector
    private val REQUEST_CAMERA_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 101
    private lateinit var photoFile: File

    private lateinit var postImageView: ImageView
    private lateinit var postCaptionView: TextView
    private lateinit var profileImage: ImageView

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Inisialisasi elemen UI
        postImageView = findViewById(R.id.postImage)
        postCaptionView = findViewById(R.id.postCaption)
        profileImage = findViewById(R.id.overlay_image) // Pastikan Anda memiliki TextView untuk username

        databaseRef = FirebaseDatabase.getInstance().getReference("posts")
        auth = FirebaseAuth.getInstance()

        // Panggil loadUserData setelah inisialisasi elemen UI
        loadUserData()

        profileImage.setOnClickListener {
            val intent = Intent(this, ProfilePageActivity::class.java)
            startActivity(intent)
        }

        val everyoneButton: Button = findViewById(R.id.everyoneButton)
        everyoneButton.setOnClickListener {
            Toast.makeText(this, "Everyone button clicked!", Toast.LENGTH_SHORT).show()
        }

        val messageIcon: View = findViewById(R.id.messageIcon)
        messageIcon.setOnClickListener {
            Toast.makeText(this, "Message Icon Clicked!", Toast.LENGTH_SHORT).show()
        }

        val messageInput: EditText = findViewById(R.id.messageInput)
        val sendMessageIcon: View = findViewById(R.id.sendMessageIcon)

        sendMessageIcon.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                Toast.makeText(this, "Pesan terkirim: $message", Toast.LENGTH_SHORT).show()
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "Pesan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
            userRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        // Memuat gambar profil jika tersedia
                        if (it.profileImageUrl != null) {
                            Glide.with(this)
                                .load(it.profileImageUrl)
                                .apply(RequestOptions().transform(CircleCrop()))
                                .into(profileImage)
                        }
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 == null) return false

        val diffX = e2.x - e1.x
        if (diffX > 50) {
            checkCameraPermission()
            return true
        }
        return false
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            Toast.makeText(this, "Izin kamera diperlukan untuk mengambil gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCamera() {
        photoFile = File.createTempFile("photo", ".jpg", cacheDir)
        val photoURI = FileProvider.getUriForFile(
            this, "com.example.uts_lec.fileprovider", photoFile
        )
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        }
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val intent = Intent(this, AddCaptionActivity::class.java)
            intent.putExtra("capturedImagePath", photoFile.absolutePath)
            startActivity(intent)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDown(e: MotionEvent): Boolean = true
    override fun onShowPress(e: MotionEvent) {}
    override fun onSingleTapUp(e: MotionEvent): Boolean = true
    override fun onScroll(
        e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float
    ): Boolean = true
    override fun onLongPress(e: MotionEvent) {}

    data class Post(
        val imageUrl: String = "",
        val caption: String = ""
    )
}