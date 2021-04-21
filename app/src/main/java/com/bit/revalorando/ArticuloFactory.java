package com.bit.revalorando;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.models.ArticuloViewModel;

public class ArticuloFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public ArticuloFactory(@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == ArticuloViewModel.class){
            return (T) new ArticuloViewModel(application);
        }
        return null;
    }
}
