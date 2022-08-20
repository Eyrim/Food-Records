package me.eyrim.foodrecords.create

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.eyrim.foodrecords.databinding.ActivityCreateBinding
import me.eyrim.foodrecords.databinding.ActivityMainBinding
import me.eyrim.foodrecords.util.databases.Database
import java.io.File

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding;
    private var db: Database = Database("records.db");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///////////

        binding.recordNameEditText.setOnFocusChangeListener {view, hasFocus ->
            if (!hasFocus) {
                // Save the new value as the new recipe name

            }
        }
    }
}