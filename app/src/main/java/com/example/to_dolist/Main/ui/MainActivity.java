package com.example.to_dolist.Main.ui;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.to_dolist.Main.utils.MainViewModelFactory;
import com.example.to_dolist.Main.utils.Repository;
import com.example.to_dolist.Main.utils.TaskAdapter;
import com.example.to_dolist.Main.viewmodel.MainViewModel;
import com.example.to_dolist.R;
import com.example.to_dolist.core.database.TaskDatabase;
import com.example.to_dolist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private TaskAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Repository repository=new Repository(new TaskDatabase(this));
        MainViewModelFactory factory = new MainViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        adapter = new TaskAdapter(new ArrayList<>(), this,viewModel);
        binding.recyclerViewTasks.setAdapter(adapter);
        binding.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getAllTasks().observe(this, tasks -> {
            adapter.updateTasks(tasks);
        });
        binding.btnAdd.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_edit_task);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
            EditText editTextTaskName = dialog.findViewById(R.id.editTextTaskName);
            dialog.findViewById(R.id.btnWrite).setOnClickListener(v1 -> {
                String title = editTextTaskName.getText().toString();
                if (title.isEmpty()) {
                    editTextTaskName.setError("Please enter a title");
                    return;
                }
                if(viewModel.addTask(title)==1){
                    Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Task addition failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                dialog.dismiss();
            });
            dialog.findViewById(R.id.btnCancel).setOnClickListener(v12 -> dialog.dismiss());
        });
    }
}