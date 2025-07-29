package com.example.to_dolist;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
    private Database database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = new Database(this);
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first_run", true)) {
            startActivity(new Intent(this, Greeting.class));
            sharedPreferences.edit().putBoolean("first_run", false).apply();
        }
        initButton(R.id.btnAdd, AdditionActivity.class);
        initButton(R.id.btnUpdate, UpdateActivity.class);
        initButton(R.id.btnDelete, DeletionActivity.class);
        initButton(R.id.btnView, Viewing.class);
    }
    private void initButton(int buttonId, Class<?> targetActivity) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, targetActivity)));
    }
}