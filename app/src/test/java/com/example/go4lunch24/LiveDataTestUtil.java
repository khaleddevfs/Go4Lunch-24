package com.example.go4lunch24;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


class LiveDataTestUtil {

    /**
     * Returns the value of a [LiveData] as soon as it gets one or waits for it to have one with a timeout.
     */
    static <T> T getOrAwaitValue(final LiveData<T> liveData) throws InterruptedException {
        final AtomicReference<T> data = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data.set(o);
                latch.countDown();
            }
        };

        try {
            liveData.observeForever(observer);

            // Wait until the LiveData is set or timeout occurs.
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw new RuntimeException("LiveData value was never set.");
            }

            return data.get();
        } finally {
            liveData.removeObserver(observer);
        }
    }

    /**
     * Returns the last value of a [LiveData] or waits for it to have one with a timeout.
     */
    static <T> T awaitValue(final LiveData<T> liveData) throws InterruptedException {
        final AtomicReference<T> data = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data.set(o);
                latch.countDown();
            }
        };

        try {
            liveData.observeForever(observer);

            // Wait until the LiveData is set or timeout occurs.
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw new RuntimeException("LiveData value was never set.");
            }

            return data.get();
        } finally {
            liveData.removeObserver(observer);
        }
    }
}
