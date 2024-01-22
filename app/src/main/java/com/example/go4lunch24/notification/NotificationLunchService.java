package com.example.go4lunch24.notification;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.go4lunch24.R;
import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.SaveDataRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.example.go4lunch24.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.ArrayList;
import java.util.List;

public class NotificationLunchService extends BroadcastReceiver {

    private final String TAG = NotificationLunchService.class.getSimpleName();

    private WorkMatesRepository workMatesRepository;
    private SaveDataRepository saveDataRepository;

    private String restaurantName;
    private String restaurantAddress;
    private String usersJoining;
    private Context context;
    private WorkMate currentUser;
    private String currentUserId;
    private List<WorkMate> workMates;

    private final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("lodi", "onReceive done");
        this.context = context;
        this.configureRepositories();
        Log.d("lodi", "Notification intent receive");
    }

    private void configureRepositories() {
        workMatesRepository = WorkMatesRepository.getInstance();
        saveDataRepository = SaveDataRepository.getInstance();
        saveDataRepository.configureContext(context);
        currentUserId = workMatesRepository.getActualUser().getUid();
        if (saveDataRepository.getNotificationSettings(currentUserId) && getCurrentUser() != null) {
            this.fetchUsers();
        }
    }

    private void fetchUsers() {
        workMates = new ArrayList<>();
        workMatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        Log.d("lodi", "fetchUsers from firbase");
                        assert workMate != null;
                        if (workMate.getWorkMateRestaurantChoice() != null && workMate.getWorkMateRestaurantChoice().getRestaurantId() != null) {


                            if (workMate.getUid().equals(currentUserId)) {
                                currentUser = workMate;
                            } else {
                                workMates.add(workMate);
                            }
                        }
                    }
                    fetchUsersGoing();
                    Log.d("lodi", "fetchUsers");
                });
    }

    private void fetchUsersGoing() {

        if (currentUser != null) {
            Log.d("lodi", "users fetching");
            List<String> usersName = new ArrayList<>();
            for (WorkMate workMate : workMates) {
                if (workMate.getWorkMateRestaurantChoice() != null && currentUser.getWorkMateRestaurantChoice() != null &&
                        workMate.getWorkMateRestaurantChoice().getRestaurantId().equals(currentUser.getWorkMateRestaurantChoice().getRestaurantId())) {
                    usersName.add(workMate.getName());
                }
            }
            restaurantName = currentUser.getName();
            assert currentUser.getWorkMateRestaurantChoice() != null;
            restaurantAddress = currentUser.getWorkMateRestaurantChoice().getRestaurantAddress();
            usersJoining = Utils.convertListToString(usersName);
            showNotification();
            Log.d("lodi", "fetchUsersGoing");
        }
    }

    @Nullable
    private FirebaseUser getCurrentUser() {
        Log.d("lodi", "from firebase done");
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    // -------------------
    // SHOW NOTIFICATION
    // -------------------

 @SuppressLint({"MissingPermission"})
    private void showNotification() {


     Log.d("lodi", "show notis done");
        String channelId = context.getString(R.string.notificationChannel);
        String message;
        String messageBody;
        if (usersJoining != null && usersJoining.length() > 0) {
            message = context.getString(R.string.notification_message);
            messageBody = String.format(message, restaurantName, usersJoining, restaurantAddress);
        } else {
            message = context.getString(R.string.message_notification_alone);
            messageBody = String.format(message, restaurantName, restaurantAddress);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_logo_go4lunch)
                .setContentTitle(context.getString(R.string.title_notification))
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody));
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
     Log.d("lodi", "show notifications");
    }
}
