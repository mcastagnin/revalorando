package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.daos.ArticuloDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Articulo;

import java.util.List;

public class ArticuloRepository {
    private ArticuloDao articuloDao;

    private LiveData<List<Articulo>> articulos;

    public ArticuloRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        articuloDao = db.articuloDao();
        articulos = articuloDao.getAll();

    }

    public LiveData<List<Articulo>> getArticulos(){
        return articulos;
    }

    public void insert(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.insert(articulo);
        });
    }
}
