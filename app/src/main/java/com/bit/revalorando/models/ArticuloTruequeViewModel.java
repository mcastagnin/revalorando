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
    private final LiveData<List<Articulo>> misTruequesDisponibles;

    //private final List<Articulo> listTrueques;


    public ArticuloTruequeViewModel(Application application, String nombreArticulo){
        super(application);
        articuloTruequeRepository = new ArticuloTruequeRepository(application, nombreArticulo);
        articulosTrueques = articuloTruequeRepository.findTruequesDisponibles();
        misTruequesDisponibles =articuloTruequeRepository.findMisTruequesDisponibles();
    }


    public LiveData<List<Articulo>> findTruequesDisponibles() {

        return articulosTrueques;
    }


    public LiveData<List<Articulo>> findMisTruequesDisponibles() {

        return misTruequesDisponibles;
    }

    public void insert(Articulo articulo){
        articuloTruequeRepository.insert(articulo);
    }

}

