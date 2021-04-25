package com.bit.revalorando.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.repositories.ArticuloRepository;

import java.util.List;

public class ArticuloViewModel extends AndroidViewModel {

    private ArticuloRepository articuloRepository;

    private final LiveData<List<Articulo>> articulos;
    //private final List<Articulo> listTrueques;
    private Articulo articulo;


    public ArticuloViewModel(Application application){
        super(application);
        articuloRepository = new ArticuloRepository(application);
        articulos = articuloRepository.getArticulos();

    }

    public LiveData<List<Articulo>> getArticulos(){
        return articulos;
    }

    public Articulo getArticulo(int id){
        articulo = articuloRepository.buscarArticulo(id);

        return articulo;
    }




    public void insert(Articulo articulo){
        articuloRepository.insert(articulo);
    }

    public void update(Articulo articulo){
        articuloRepository.update(articulo);
    }

    public void delete(Articulo articulo){
        articuloRepository.delete(articulo);
    }
}

