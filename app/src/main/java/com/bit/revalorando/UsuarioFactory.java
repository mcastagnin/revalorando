package com.bit.revalorando;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.models.UsuarioViewModel;

public class UsuarioFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public UsuarioFactory (@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == UsuarioViewModel.class){
            return (T) new UsuarioViewModel(application);
        }
        return null;
    }
}
