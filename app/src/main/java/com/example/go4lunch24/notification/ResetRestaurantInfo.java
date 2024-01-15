package com.example.go4lunch24.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

public class ResetRestaurantInfo  extends BroadcastReceiver {

    private WorkMatesRepository workMatesRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        workMatesRepository = WorkMatesRepository.getINSTANCE();
        this.deleteRestaurantInfo();
    }

    private void deleteRestaurantInfo() {
        workMatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        if (workMate != null && workMate.getWorkMateRestaurantChoice().getRestaurantId() != null) {
                            workMatesRepository.updateRestaurantPicked(null, null, null, workMate.getUid());
                        }
                    }
                });
    }
}
