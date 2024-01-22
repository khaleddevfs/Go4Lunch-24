package com.example.go4lunch24.setting;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import com.example.go4lunch24.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationTests {

    @Mock
    private Context mockContext;

    @Mock
    private NotificationManager mockNotificationManager;

    @Mock
    private PackageManager mockPackageManager;

    @Mock
    private AlarmManager mockAlarmManager;

    @Mock
    private PendingIntent mockPendingIntent;

    @InjectMocks
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivity = new MainActivity();
    }

    @Test
    public void testConfigureNotifications() {
        mainActivity.configureNotifications();

        // You can use Mockito.verify() to check if the expected methods are called.
        verify(mainActivity).createNotificationChannel();
        verify(mainActivity).configureNotificationIntent();
        verify(mainActivity).enableNotification();
    }

    @Test
    public void testCreateNotificationChannel() {
        // Simulate Android version
        when(Build.VERSION.SDK_INT).thenReturn(Build.VERSION_CODES.O);

        // Call the method to be tested
        mainActivity.createNotificationChannel();

        // Verify that NotificationManager.createNotificationChannel is called
        verify(mockNotificationManager).createNotificationChannel(any(NotificationChannel.class));
    }

    @Test
    public void testConfigureNotificationIntent() {
        mainActivity.configureNotificationIntent();

        // Verify that PendingIntent.getBroadcast is called
        verify(mockContext).getSystemService(Context.ALARM_SERVICE);
        verify(mockPendingIntent).getBroadcast(eq(mockContext), eq(0), any(Intent.class), eq(PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE));
    }

    @Test
    public void testEnableNotification() {
        mainActivity.enableNotification();

        // Verify that PackageManager.setComponentEnabledSetting is called
        verify(mockContext).getPackageManager();
        verify(mockPackageManager).setComponentEnabledSetting(any(ComponentName.class), eq(PackageManager.COMPONENT_ENABLED_STATE_ENABLED), eq(PackageManager.DONT_KILL_APP));

        // Verify that AlarmManager.setInexactRepeating is called
        verify(mockAlarmManager).setInexactRepeating(
                eq(AlarmManager.RTC_WAKEUP),
                anyLong(),
                eq(AlarmManager.INTERVAL_DAY),
                eq(mockPendingIntent)
        );
    }
}
