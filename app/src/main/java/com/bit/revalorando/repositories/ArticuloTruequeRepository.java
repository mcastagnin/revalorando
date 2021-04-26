package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.VariablesLogin;
import com.bit.revalorando.daos.ArticuloDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Articulo;

import java.util.List;

public class ArticuloTruequeRepository {
    private ArticuloDao articuloDao;
    VariablesLogin vLogin = VariablesLogin.getInstance();
    public String busqueda = vLogin.busquedaGlobal;

    public LiveData<List<Articulo>>  truequesDisponibles;
    public LiveData<List<Articulo>>  misTruequesDisponibles;


    public ArticuloTruequeRepository(Application application, String nombreArticulo){
        AppDatabase db = AppDatabase.getInstance(application);
        articuloDao = db.articuloDao();
        truequesDisponibles = articuloDao.findTruequesDisponibles(busqueda, vLogin.idUsuarioGlobal);
        misTruequesDisponibles = articuloDao.findMisTruequesDisponibles(vLogin.idUsuarioGlobal);

    }

    public LiveData<List<Articulo>> findTruequesDisponibles() { return truequesDisponibles; }

    public LiveData<List<Articulo>> findMisTruequesDisponibles() { return misTruequesDisponibles; }


    public void insert(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.insert(articulo);
        });
    }
}
