package com.example.to_dolist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class DeletionActivity extends AppCompatActivity {
    private EditText etIdDelete;
    private Button btnSubmitDelete, btnBackDelete;
    private Database database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deletion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etIdDelete = findViewById(R.id.etIdDelete);
        btnSubmitDelete = findViewById(R.id.btnSubmitDelete);
        btnBackDelete = findViewById(R.id.btnBackDelete);
        database = new Database(DeletionActivity.this);
        btnSubmitDelete.setOnClickListener(v -> {
            String idText = etIdDelete.getText().toString().trim();
            if (idText.isEmpty()) {
                Toast.makeText(this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                return;
            }
            int id;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID format", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!database.isValidId(id)) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                return;
            }
            int rowsDeleted = database.delete(id);
            if (rowsDeleted > 0) {
                Toast.makeText(this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error deleting task", Toast.LENGTH_SHORT).show();
            }
        });
        btnBackDelete.setOnClickListener(v -> finish());
    }
}