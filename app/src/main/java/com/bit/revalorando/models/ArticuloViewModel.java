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
    //private final LiveData<List<Articulo>> listTrueques;


    public ArticuloViewModel(Application application){
        super(application);
        articuloRepository = new ArticuloRepository(application);
        articulos = articuloRepository.getArticulos();

        //listTrueques = articuloRepository.findTruequesDisponibles("");
    }

    public LiveData<List<Articulo>> getArticulos(){
        return articulos;
    }

    /*public LiveData<List<Articulo>>  findTruequesDisponibles(String nombreArticulo) {
        return listTrueques;
    }*/


    public void insert(Articulo articulo){
        articuloRepository.insert(articulo);
    }
}

