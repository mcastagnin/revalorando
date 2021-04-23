package com.bit.revalorando;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.models.ArticuloTruequeViewModel;
import com.bit.revalorando.models.ArticuloViewModel;

public class ArticuloTruequeFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;
    private String nombreArticulo;

    public ArticuloTruequeFactory(@NonNull Application application,String nombreArt){
        this.application = application;
        nombreArticulo = nombreArt;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == ArticuloTruequeViewModel.class){
            return (T) new ArticuloTruequeViewModel(application, nombreArticulo);
        }
        return null;
    }
}
