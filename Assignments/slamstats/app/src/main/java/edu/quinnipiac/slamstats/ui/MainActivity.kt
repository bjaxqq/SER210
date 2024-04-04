package edu.quinnipiac.slamstats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.quinnipiac.slamstats.R
import edu.quinnipiac.slamstats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
