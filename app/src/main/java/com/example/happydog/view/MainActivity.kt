package com.example.happydog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happydog.R
import com.example.happydog.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, BreedsListFragment())
            .commit()
    }
}