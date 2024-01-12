package com.example.go4lunch24.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go4lunch24.databinding.WorkmatesItemLayoutBinding;
import com.example.go4lunch24.models.WorkMate;

import java.util.List;

public class WorkMateAdapter extends RecyclerView.Adapter<WorkMateViewHolder> {

    private List<WorkMate> workMateList;

    public WorkMateAdapter(List<WorkMate> workMateList) {
        this.workMateList = workMateList;
    }

    @NonNull
    @Override
    public WorkMateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkmatesItemLayoutBinding binding = WorkmatesItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WorkMateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkMateViewHolder holder, int position) {
        holder.updateWorkMates(workMateList.get(position));
    }

    @Override
    public int getItemCount() {
        if (workMateList != null) {
            return workMateList.size();
        }
        return 0;
    }

    public void updateWorkMates(List<WorkMate> workMates) {
        this.workMateList = workMates;
        notifyDataSetChanged();
    }
}
