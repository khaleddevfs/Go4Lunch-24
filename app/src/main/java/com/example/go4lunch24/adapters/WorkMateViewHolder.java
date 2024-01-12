package com.example.go4lunch24.adapters;

import android.content.Context;
import android.graphics.Typeface;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.go4lunch24.R;
import com.example.go4lunch24.models.WorkMate;

public class WorkMateViewHolder extends RecyclerView.ViewHolder {


    public com.example.go4lunch24.databinding.WorkmatesItemLayoutBinding binding;

    public WorkMateViewHolder(com.example.go4lunch24.databinding.WorkmatesItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void updateWorkMates(WorkMate workMate) {
        Context context = binding.getRoot().getContext();
        String workMateTextName = "";
        if (workMate.getWorkMateRestaurantChoice() != null) {
            workMateTextName = workMate.getName() + " " + context.getString(R.string.is_eating) + " (" + workMate.getWorkMateRestaurantChoice().getRestaurantName() + ")";
            binding.nameTextView.setTypeface(binding.nameTextView.getTypeface(), Typeface.BOLD);
            binding.nameTextView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        } else {
            binding.nameTextView.setTypeface(binding.nameTextView.getTypeface(), Typeface.ITALIC);
            binding.nameTextView.setTextColor(context.getResources().getColor(R.color.colorGrey));
            workMateTextName = workMate.getName() + " " + context.getString(R.string.has_not_decided_yet);
        }
        binding.nameTextView.setText(workMateTextName);
        Glide.with(binding.recyclerViewWorkMateItemImageView.getContext())
                .load(workMate.getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.recyclerViewWorkMateItemImageView);
    }
}
