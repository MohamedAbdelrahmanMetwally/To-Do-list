package com.example.to_dolist;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class AdditionActivity extends AppCompatActivity {
    private Button btnSubmitAdd, btnBackAdd;
    private EditText etTitle, etDescription;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addition);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etTitle = findViewById(R.id.etTitleAdd);
        etDescription = findViewById(R.id.etDescriptionAdd);
        btnSubmitAdd = findViewById(R.id.btnSubmitAdd);
        btnBackAdd = findViewById(R.id.btnBackAdd);
        btnSubmitAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            if (title.isEmpty()||description.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            Database database = new Database(AdditionActivity.this);
            long result = database.insert(title, description);
            if (result != -1) {
                Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error adding task", Toast.LENGTH_SHORT).show();
            }
        });
        btnBackAdd.setOnClickListener(v -> finish());
    }
}