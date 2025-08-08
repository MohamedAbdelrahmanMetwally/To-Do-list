package com.example.to_dolist.Main.utils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.to_dolist.core.database.Task;
import com.example.to_dolist.core.database.TaskDatabase;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private  TaskDatabase taskDatabase;
    public Repository(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
    }
    public long addTask(String title) {
        return taskDatabase.addTask(title);
    }
    public int updateTask(int id, String title) {
        return taskDatabase.updateTask(id, title);
    }
    public int deleteTask(int id) {
        return taskDatabase.deleteTask(id);
    }
    public LiveData<ArrayList<Task>> getAllTasks() {
        MutableLiveData<ArrayList<Task>> liveTasks = new MutableLiveData<>();
        ArrayList<Task> tasks = taskDatabase.getAllTasks();
        liveTasks.setValue(tasks);
        return liveTasks;
    }
}