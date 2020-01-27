package com.example.croesus.views.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class QuestionViewModelFactory implements ViewModelProvider.Factory {

    private final Application app;
    private final int id;

    public QuestionViewModelFactory(Application application, int id) {
        this.app = application;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuestionViewModel(app, id);
    }
}
