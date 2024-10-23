package com.example.uts_lec

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.activity.ComponentActivity
import android.graphics.Typeface
import android.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menautkan XML layout dengan setContentView()
        setContentView(R.layout.activity_main)

        // Temukan TextView berdasarkan ID
        val textView1: TextView = findViewById(R.id.textView1)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)

        // Terapkan font custom ke TextView
        val regularFont = ResourcesCompat.getFont(this, R.font.sora)
        val boldFont = ResourcesCompat.getFont(this, R.font.sora_bold)

        // Mengatur font regular untuk TextView
        if (regularFont != null) {
            textView1.typeface = boldFont
            textView2.typeface = regularFont
            textView3.typeface = regularFont // Mengatur font regular untuk textView3
        }

        // Membuat teks "Already registered? Sign In"
        val text = "Already registered? Sign In"

        // Membuat SpannableString
        val spannableString = SpannableString(text)

        // Mengatur span untuk "Sign In"
        val startIndex = text.indexOf("Sign In")
        val endIndex = startIndex + "Sign In".length

        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#B3FFF6EF")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Membuat ClickableSpan untuk teks "Sign In"
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Arahkan pengguna ke halaman Sign In
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true // Menambahkan garis bawah jika diinginkan
            }
        }

        // Set span untuk warna dan bold
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set spannableString ke TextView
        textView3.text = spannableString

        // Menambahkan LinkMovementMethod agar teks bisa diklik
        textView3.movementMethod = LinkMovementMethod.getInstance()

        // Menambahkan aksi pada tombol Sign Up
        val signUpButton: Button = findViewById(R.id.btnSignUp)
        signUpButton.setOnClickListener {
            // Ke halaman SignUp
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}