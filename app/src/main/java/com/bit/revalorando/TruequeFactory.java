package com.bit.revalorando;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.models.TruequeViewModel;

public class TruequeFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public TruequeFactory(@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == TruequeViewModel.class){
            return (T) new TruequeViewModel(application);
        }
        return null;
    }
}
