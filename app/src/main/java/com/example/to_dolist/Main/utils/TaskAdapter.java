package com.example.to_dolist.Main.utils;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.Main.viewmodel.MainViewModel;
import com.example.to_dolist.R;
import com.example.to_dolist.core.database.SharedPref;
import com.example.to_dolist.core.database.Task;
import com.example.to_dolist.core.database.TaskDatabase;

import java.util.Collections;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<Task> tasks;
    TaskDatabase db;
    Repository repository;
    MainViewModel viewModel;
    MainViewModelFactory factory;
    public TaskAdapter(List<Task> tasks, Context context, MainViewModel viewModel) {
        this.tasks = tasks;
        this.context = context;
        if(SharedPref.getInstance(context).getOrder().equals("desc")){
            Collections.reverse(tasks);
        }
        this.viewModel = viewModel;
    }
    Context context;
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTaskName.setText(task.getTitle());
        holder.btnDelete.setOnClickListener(v -> {
            TaskDatabase db = new TaskDatabase(context);
            if(viewModel.deleteTask(task.getId())==1){
                Toast.makeText(context, "Task deleted successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Task deletion failed", Toast.LENGTH_SHORT).show();
                return;
            }
            /*
            tasks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tasks.size());
             */
        });
        holder.btnUpdate.setOnClickListener(v -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_add_edit_task);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
            Button btnWrite = dialog.findViewById(R.id.btnWrite);
            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            btnWrite.setOnClickListener(v1 -> {
                String newTitle = ((TextView) dialog.findViewById(R.id.editTextTaskName)).getText().toString();
                TaskDatabase db = new TaskDatabase(context);
                if(newTitle.isEmpty()){
                    Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(viewModel.updateTask(task.getId(), newTitle)==1){
                    Toast.makeText(context, "Task updated successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Task update failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                /*
                task.setTitle(newTitle);
                notifyItemChanged(position);*/
                dialog.dismiss();
            });
            btnCancel.setOnClickListener(v12 -> dialog.dismiss());
        });
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvTaskName;
        Button btnDelete;
        Button btnUpdate;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
    public void updateTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
}