package com.bit.revalorando.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.repositories.ArticuloRepository;
import com.bit.revalorando.repositories.ArticuloTruequeRepository;

import java.util.List;

public class ArticuloTruequeViewModel extends AndroidViewModel {

    private ArticuloTruequeRepository articuloTruequeRepository;

    private final LiveData<List<Articulo>> articulosTrueques;
    //private final List<Articulo> listTrueques;


    public ArticuloTruequeViewModel(Application application, String nombreArticulo){
        super(application);
        articuloTruequeRepository = new ArticuloTruequeRepository(application, nombreArticulo);
        articulosTrueques = articuloTruequeRepository.findTruequesDisponibles();

    }


    public LiveData<List<Articulo>> findTruequesDisponibles() {

        return articulosTrueques;
    }


    public void insert(Articulo articulo){
        articuloTruequeRepository.insert(articulo);
    }
}

