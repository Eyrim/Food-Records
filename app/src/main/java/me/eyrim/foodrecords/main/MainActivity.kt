package me.eyrim.foodrecords.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import me.eyrim.foodrecords.create.CreateActivity
import me.eyrim.foodrecords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///////////

        binding.createRecipeImageView.setOnClickListener {view ->
            val intent: Intent = Intent(this, CreateActivity::class.java);
            this.startActivity(intent);
        }
    }
}