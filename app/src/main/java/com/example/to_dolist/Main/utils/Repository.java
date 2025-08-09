package com.example.to_dolist.Main.utils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.to_dolist.core.database.Task;
import com.example.to_dolist.core.database.TaskDatabase;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private  TaskDatabase taskDatabase;
    private final MutableLiveData<List<Task>> tasksLiveData = new MutableLiveData<>();
    public Repository(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
        loadTasks();
    }
    private void loadTasks() {
        List<Task> tasks = taskDatabase.getAllTasks();
        tasksLiveData.setValue(tasks);
    }
    public long addTask(String title) {
        if(taskDatabase.addTask(title)>0){
            loadTasks();
            return 1;
        }else{
            return 0;
        }
    }
    public int updateTask(int id, String title) {
        if(taskDatabase.updateTask(id,title)>0){
            loadTasks();
            return 1;
        }else{
            return 0;
        }
    }
    public int deleteTask(int id) {
        if(taskDatabase.deleteTask(id)>0){
            loadTasks();
            return 1;
        }else{
            return 0;
        }
    }
    public LiveData<List<Task>> getAllTasks() {
        return tasksLiveData;
    }
}