package com.example.to_dolist.Main.utils;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.Database;
import com.example.to_dolist.R;
import com.example.to_dolist.core.database.SharedPref;
import com.example.to_dolist.core.database.Task;
import com.example.to_dolist.core.database.TaskDatabase;

import java.util.ArrayList;
import java.util.Collections;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<Task> tasks;
    public TaskAdapter(ArrayList<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
        if(SharedPref.getInstance(context).getOrder().equals("desc")){
            Collections.reverse(tasks);
        }
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
            db.deleteTask(task.getId());
            tasks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tasks.size());
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
                db.updateTask(task.getId(), newTitle);
                task.setTitle(newTitle);
                notifyItemChanged(position);
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
}