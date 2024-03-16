package com.example.notes21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes21.databinding.FragmentNotesBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}