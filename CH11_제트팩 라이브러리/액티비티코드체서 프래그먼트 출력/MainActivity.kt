package com.example.fragment_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragment_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()

        transaction.add(R.id.fragment_content, fragment)
        transaction.commit()
    }
}
