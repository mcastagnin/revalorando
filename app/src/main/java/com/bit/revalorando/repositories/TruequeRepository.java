package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.daos.TruequeDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;

import java.util.List;

public class TruequeRepository {
    private TruequeDao truequeDao;

    private LiveData<List<Trueque>> trueques;

    public TruequeRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        truequeDao = db.truequeDao();
        trueques = truequeDao.getAll();

    }

    public LiveData<List<Trueque>> getTrueques(){
        return trueques;
    }

    public void insert(Trueque trueque){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            truequeDao.insert(trueque);
        });
    }

    public void update(Trueque trueque){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            truequeDao.update(trueque);
        });
    }

    public Trueque buscarTrueque(int id){
        Trueque trueque = null;
        trueque = truequeDao.findById(id);

        return trueque;
    }

    public void deleteTruequeByArticuloId(int id){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            truequeDao.deleteTruequeByArticuloId(id);
        });
    }

    public void updateTruequeByTruequeId(int idArticulo2, int idUsuario2, int idTrueque){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            truequeDao.updateTruequeByTruequeId(idArticulo2,idUsuario2,idTrueque);
        });
    }

    public void delete(Trueque trueque){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            truequeDao.delete(trueque);
        });
    }
}
