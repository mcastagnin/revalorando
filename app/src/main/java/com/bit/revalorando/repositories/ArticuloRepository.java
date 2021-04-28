package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.VariablesLogin;
import com.bit.revalorando.daos.ArticuloDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Usuario;

import java.util.List;

public class ArticuloRepository {
    private ArticuloDao articuloDao;
    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

    private LiveData<List<Articulo>> articulos;
    public LiveData<List<Articulo>>  misTruequesDisponibles;
    public LiveData<List<Articulo>>  misTruequesOfertados;



    public ArticuloRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        articuloDao = db.articuloDao();
        //articulos = articuloDao.getAll();
        articulos = articuloDao.findMisArticulos(idUsuario);
        misTruequesDisponibles = articuloDao.findMisTruequesDisponibles(vLogin.idUsuarioGlobal);
        misTruequesOfertados = articuloDao.findMisTruequesOfertados(vLogin.idUsuarioGlobal);



    }

    public LiveData<List<Articulo>> getArticulos(){
        return articulos;
    }


    public LiveData<List<Articulo>> findMisTruequesDisponibles() { return misTruequesDisponibles; }

    public LiveData<List<Articulo>> findMisTruequesOfertados() { return misTruequesOfertados; }



    public Articulo buscarArticulo(int id){
        Articulo articulo = null;
        articulo = articuloDao.findById(id);

        return articulo;
    }

    public void insert(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.insert(articulo);
        });
    }

    public void update(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.update(articulo);
        });
    }



    public void delete(Articulo articulo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articuloDao.delete(articulo);
        });
    }
}
