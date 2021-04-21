package com.bit.revalorando.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.repositories.ArticuloRepository;

import java.util.List;

public class ArticuloViewModel extends AndroidViewModel {

    private ArticuloRepository articuloRepository;
    private final LiveData<List<Articulo>> articulos;

    public ArticuloViewModel(Application application){
        super(application);
        articuloRepository = new ArticuloRepository(application);
        articulos = articuloRepository.getArticulos();
    }

    public LiveData<List<Articulo>> getArticulos(){
        return articulos;
    }

    public void insert(Articulo articulo){
        articuloRepository.insert(articulo);
    }
}

