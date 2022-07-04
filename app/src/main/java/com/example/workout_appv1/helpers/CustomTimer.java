package com.example.workout_appv1.helpers;

import android.os.CountDownTimer;

public abstract class CustomTimer extends CountDownTimer {
    public CustomTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long convertedTime = millisUntilFinished / 1000;
        getRemainingTime(convertedTime);

    }

    @Override
    public void onFinish() {
        getRemainingTime(0);
    }

    public abstract void getRemainingTime(long remainingTime);
}
