package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.daos.ArticuloDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Articulo;

import java.util.List;

public class ArticuloTruequeRepository {
    private ArticuloDao articuloDao;

    public LiveData<List<Articulo>>  truequesDisponibles;

    public ArticuloTruequeRepository(Application application, String nombreArticulo){
        AppDatabase db = AppDatabase.getInstance(application);
        articuloDao = db.articuloDao();
        truequesDisponibles = articuloDao.findTruequesDisponibles(nombreArticulo);

    }

    public LiveData<List<Articulo>> findTruequesDisponibles() { return truequesDisponibles; }


    public void insert(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.insert(articulo);
        });
    }
}
