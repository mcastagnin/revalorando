package com.bit.revalorando.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.entities.Articulo;


import java.util.List;

@Dao
public interface TruequeDao {

    @Query("SELECT * from Trueque")
    LiveData<List<Trueque>> getAll();

    @Query("SELECT * FROM articulo " +
            "INNER JOIN trueque ON trueque.idArticulo1 = articulo.id " +
            "WHERE articulo.nombre LIKE :nombreArticulo AND trueque.idArticulo2 IS NULL")
    public List<Articulo> findTruequesDisponibles(String nombreArticulo);

    @Insert
    void insert(Trueque trueque);

    @Update
    void update (Trueque trueque);

    @Delete
    void delete (Trueque trueque);

    @Query("DELETE FROM trueque WHERE idArticulo1 = :id")
    void deleteTruequeByArticuloId(int id);

    @Query("SELECT * FROM trueque where id = :id")
    Trueque findById(int id);

    @Query(" UPDATE Trueque SET idArticulo2 = :idArticulo2, idUsuario2=:idUsuario2, estado ='p' WHERE id=:idTrueque")
    void updateTruequeByTruequeId(int idArticulo2, int idUsuario2, int idTrueque);


}
