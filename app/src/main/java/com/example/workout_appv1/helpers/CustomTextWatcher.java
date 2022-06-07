package com.example.workout_appv1.helpers;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class CustomTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.onEditTextChanged(editable);

    }
    public abstract void onEditTextChanged(Editable s);
}
