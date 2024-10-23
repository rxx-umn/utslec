package com.example.uts_lec

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tautkan layout XML
        setContentView(R.layout.activity_menu)

        // Referensi ke Gambar Profil
        val profileImage: ImageView = findViewById(R.id.profileImage)
        profileImage.setOnClickListener {
            // Aksi saat gambar profil diklik
            Toast.makeText(this, "Profil diklik", Toast.LENGTH_SHORT).show()
            // Anda bisa menambahkan intent ke halaman profil jika diinginkan
        }

        // Referensi ke Tombol Everyone
        val everyoneButton: Button = findViewById(R.id.everyoneButton)
        everyoneButton.setOnClickListener {
            // Aksi saat tombol "Everyone" diklik
            Toast.makeText(this, "Everyone button clicked!", Toast.LENGTH_SHORT).show()
        }

        // Referensi ke Ikon Pesan
        val messageIcon: ImageView = findViewById(R.id.messageIcon)
        messageIcon.setOnClickListener {
            // Aksi saat ikon pesan diklik
            Toast.makeText(this, "Message Icon Clicked!", Toast.LENGTH_SHORT).show()
            // Anda bisa menambahkan intent ke halaman pesan jika diinginkan
        }

        // Referensi ke input pesan dan tombol kirim pesan
        val messageInput: EditText = findViewById(R.id.messageInput)
        val sendMessageIcon: ImageView = findViewById(R.id.sendMessageIcon)

        sendMessageIcon.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                // Aksi kirim pesan
                Toast.makeText(this, "Pesan terkirim: $message", Toast.LENGTH_SHORT).show()

                // Reset input setelah pesan dikirim
                messageInput.text.clear()
            } else {
                // Jika input kosong
                Toast.makeText(this, "Pesan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        // Referensi ke Emoji Icon
        val emojiIcon: ImageView = findViewById(R.id.emojiIcon)
        emojiIcon.setOnClickListener {
            // Aksi saat ikon emoji diklik
            Toast.makeText(this, "Emoji Icon Clicked!", Toast.LENGTH_SHORT).show()
            // Tambahkan logika yang sesuai untuk emoji jika diperlukan
        }
    }
}
