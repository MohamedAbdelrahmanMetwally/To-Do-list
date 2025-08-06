package com.example.to_dolist;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.to_dolist.Main.MainActivity;

public class UpdateActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnSubmitUpdate = findViewById(R.id.btnSubmitUpdate);
        btnSubmitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etIdUpdate = findViewById(R.id.etIdUpdate);
                int id=Integer.parseInt(etIdUpdate.getText().toString());
                if(!new Database(UpdateActivity.this).isValidId(id)){
                    Toast.makeText(UpdateActivity.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText etTitleUpdate = findViewById(R.id.etTitleUpdate);
                String title = etTitleUpdate.getText().toString();
                EditText etDescriptionUpdate = findViewById(R.id.etDescriptionUpdate);
                String description = etDescriptionUpdate.getText().toString();
                Database database = new Database(UpdateActivity.this);
               if( database.update(id, title, description)!=-1){
                   Toast.makeText(UpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }
               else {
                   Toast.makeText(UpdateActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                   return;
               }
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btnBackUpdate = findViewById(R.id.btnBackUpdate);
        btnBackUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}