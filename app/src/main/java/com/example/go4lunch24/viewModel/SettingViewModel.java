package com.example.go4lunch24.viewModel;

import android.content.Context;

import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.SaveDataRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;


public class SettingViewModel extends BaseViewModel {

    private final SaveDataRepository saveDataRepository;

    private final WorkMate workMate;


    public SettingViewModel(SaveDataRepository saveDataRepository, WorkMatesRepository workmatesRepository) {
        this.saveDataRepository = saveDataRepository;
        this.workmatesRepository = workmatesRepository;
        workMate = workmatesRepository.getActualUser();
    }


    public void configureSaveDataRepo(Context context) {
        if (saveDataRepository.getPreferences() == null) {
            saveDataRepository.configureContext(context);
        }
    }

    public void notificationStateChanged(boolean enabled, Context context) {
        saveDataRepository.configureContext(context);
        saveDataRepository.saveNotificationSettings(enabled, workMate.getUid());
    }

    public boolean getStatusNotification(Context context) {
        saveDataRepository.configureContext(context);
        return saveDataRepository.getNotificationSettings(workMate.getUid());
    }


}
