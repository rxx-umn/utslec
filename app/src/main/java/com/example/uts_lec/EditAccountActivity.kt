package com.example.uts_lec

import android.app.DatePickerDialog
import android.content.Intent
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
import java.util.*

class EditAccountActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var birthdayInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var saveButton: Button
    private lateinit var profileImage: ImageView

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private var originalEmail: String = ""
    private var originalName: String = ""
    private var originalBirthday: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        birthdayInput = findViewById(R.id.birthdayInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        saveButton = findViewById(R.id.saveButton)
        profileImage = findViewById(R.id.profileImage)

        databaseRef = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()

        loadUserData()

        addTextWatchers()

        // Set listener untuk birthdayInput
        birthdayInput.setOnClickListener {
            showDatePickerDialog()
        }

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
                        originalBirthday = it.birthday
                        nameInput.setText(originalName)
                        emailInput.setText(originalEmail)
                        birthdayInput.setText(originalBirthday)
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
        birthdayInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)
    }

    private fun checkForChanges() {
        val isChanged = nameInput.text.toString() != originalName ||
                emailInput.text.toString() != originalEmail ||
                birthdayInput.text.toString() != originalBirthday ||
                passwordInput.text.toString().isNotEmpty()

        saveButton.visibility = if (isChanged) View.VISIBLE else View.GONE
    }

    private fun saveChanges() {
        val newName = nameInput.text.toString()
        val newEmail = emailInput.text.toString()
        val newBirthday = birthdayInput.text.toString()
        val newPassword = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (newPassword.isNotEmpty() && newPassword != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid
        if (userId != null) {
            // Update name, email, and birthday in the database
            databaseRef.child(userId).child("name").setValue(newName)
            databaseRef.child(userId).child("email").setValue(newEmail)
            databaseRef.child(userId).child("birthday").setValue(newBirthday)

            // Update password in Firebase Auth if provided
            if (newPassword.isNotEmpty()) {
                auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            // Menyimpan data pengguna ke Firebase
            val user = User(name = newName, email = newEmail, birthday = newBirthday)
            databaseRef.child(userId).setValue(user)
                .addOnCompleteListener { dbTask ->
                    if (dbTask.isSuccessful) {
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                        // Arahkan kembali ke ProfilePageActivity
                        val intent = Intent(this, ProfilePageActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish() // Tutup EditAccountActivity
                    } else {
                        Toast.makeText(this, "Gagal menyimpan data pengguna", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Format bulan dari 0-11 menjadi 1-12
            val formattedMonth = selectedMonth + 1
            birthdayInput.setText("$selectedDay/$formattedMonth/$selectedYear")
        }, year, month, day)

        datePickerDialog.show()
    }
}
