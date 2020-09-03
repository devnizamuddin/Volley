package com.e.volley.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class Factory extends ViewModelProvider.AndroidViewModelFactory {
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public Factory(@NonNull Application application) {
        super(application);
    }
}
