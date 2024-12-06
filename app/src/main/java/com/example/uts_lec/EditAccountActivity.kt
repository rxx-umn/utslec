package com.example.uts_lec

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditAccountActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var saveButton: Button
    private lateinit var profileImage: ImageView

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private var originalEmail: String = ""
    private var originalName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        saveButton = findViewById(R.id.saveButton)
        profileImage = findViewById(R.id.profileImage)

        databaseRef = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()

        loadUserData()

        addTextWatchers()

        saveButton.setOnClickListener {
            saveChanges()
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        originalName = it.name
                        originalEmail = it.email
                        nameInput.setText(originalName)
                        emailInput.setText(originalEmail)
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkForChanges()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        nameInput.addTextChangedListener(textWatcher)
        emailInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)
    }

    private fun checkForChanges() {
        val isChanged = nameInput.text.toString() != originalName ||
                emailInput.text.toString() != originalEmail ||
                passwordInput.text.toString().isNotEmpty()

        saveButton.visibility = if (isChanged) View.VISIBLE else View.GONE
    }

    private fun saveChanges() {
        val newName = nameInput.text.toString()
        val newEmail = emailInput.text.toString()
        val newPassword = passwordInput.text.toString()

        if (newPassword.isNotEmpty() && newPassword != confirmPasswordInput.text.toString()) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).child("name").setValue(newName)
            databaseRef.child(userId).child("email").setValue(newEmail)
            if (newPassword.isNotEmpty()) {
                // Update password in Firebase Auth
                auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}