package com.example.notes21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes21.databinding.ActivityMainBinding
import com.example.notes21.fragments.AddNoteFragment
import com.example.notes21.fragments.NotesFragment

class MainActivity : AppCompatActivity(), OnAddButtonClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NotesFragment())
                .commit()
        }
    }

    override fun onAddButtonClicked() {
        val addNoteFragment = AddNoteFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, addNoteFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onNoteButtonClicked() {
        supportFragmentManager.popBackStack()
    }

}