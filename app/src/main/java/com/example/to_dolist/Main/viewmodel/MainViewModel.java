package com.example.to_dolist.Main.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.to_dolist.Main.utils.Repository;
import com.example.to_dolist.core.database.Task;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private Repository repository;
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }
    public long addTask(String title) {
        return repository.addTask(title);
    }
    public int updateTask(int id, String title) {
        return repository.updateTask(id, title);
    }
    public int deleteTask(int id) {
        return repository.deleteTask(id);
    }
    public LiveData<List<Task>> getAllTasks() {
        return repository.getAllTasks();
    }
}
