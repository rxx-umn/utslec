package com.example.uts_lec

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_messages)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Data Dummy
        val messages = listOf(
            Message("Alice", "Hello! How are you?"),
            Message("Bob", "Let's meet tomorrow"),
            Message("Charlie", "Check your email!")
        )

        recyclerView.adapter = MessageAdapter(messages)

        // Back Button Functionality
        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}