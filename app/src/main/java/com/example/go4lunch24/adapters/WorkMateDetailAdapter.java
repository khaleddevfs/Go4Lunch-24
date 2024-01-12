package com.example.go4lunch24.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go4lunch24.databinding.WorkmatesItemLayoutBinding;
import com.example.go4lunch24.models.WorkMate;

import java.util.List;

public class WorkMateDetailAdapter extends RecyclerView.Adapter<WorkMateDetailViewHolder> {

    private List<WorkMate> workMateList;


    @NonNull
    @Override
    public WorkMateDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkmatesItemLayoutBinding binding = WorkmatesItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WorkMateDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkMateDetailViewHolder holder, int position) {
        holder.updateWorkMates(workMateList.get(position));
    }

    @Override
    public int getItemCount() {
        if (workMateList != null) {
            return workMateList.size();
        }
        return 0;
    }

    public void setWorkMateList(List<WorkMate> coworkerLists) {
        this.workMateList = coworkerLists;
        notifyDataSetChanged();
    }
}
