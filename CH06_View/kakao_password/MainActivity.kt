package com.example.kakao_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakao_password.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}