package com.bit.revalorando.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bit.revalorando.VariablesLogin;
import com.bit.revalorando.entities.Articulo;

import java.util.List;

@Dao
public interface ArticuloDao {


    @Query("SELECT * from Articulo")
    LiveData<List<Articulo>> getAll();

    @Insert
    void insert(Articulo articulo);

    @Update
    void update (Articulo articulo);

    @Delete
    void delete (Articulo articulo);

    @Query("SELECT * FROM articulo where nombre like :nombre")
    Articulo findByNombre(String nombre);

    @Query("SELECT * FROM articulo where id = :id")
    Articulo findById(int id);

    @Query("SELECT * FROM articulo " +
            "INNER JOIN trueque ON trueque.idArticulo1 = articulo.id " +
            "WHERE articulo.nombre LIKE  '%'||:nombreArticulo ||'%' AND trueque.idArticulo2= -1")
    LiveData<List<Articulo>> findTruequesDisponibles(String nombreArticulo);

    @Query("SELECT * FROM articulo " +
            "WHERE idUsuario =:idUsuario")
    LiveData<List<Articulo>> findMisArticulos(int idUsuario);
}
