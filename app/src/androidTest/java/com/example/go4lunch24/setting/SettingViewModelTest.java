package com.example.go4lunch24.setting;



import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.SaveDataRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.example.go4lunch24.viewModel.SettingViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

@RunWith(JUnit4.class)
public class SettingViewModelTest extends TestCase {

    private SettingViewModel settingViewModel;

    private WorkMate workMate;

    private Context context;
    @Mock
    private WorkMatesRepository workMatesRepository;

    @Mock
    private SaveDataRepository saveDataRepository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        workMate = new WorkMate("uid", "name", "email", "photoUrl");
        when(workMatesRepository.getActualUser()).thenReturn(workMate);
        when(saveDataRepository.getNotificationSettings(workMate.getUid())).thenReturn(true);
        settingViewModel = new SettingViewModel(saveDataRepository, workMatesRepository);
        super.setUp();
    }

}
