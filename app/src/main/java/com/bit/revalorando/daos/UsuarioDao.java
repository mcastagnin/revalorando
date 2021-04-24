package com.bit.revalorando.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bit.revalorando.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("SELECT * from Usuario")
    LiveData<List<Usuario>> getAll();

    @Insert
    void insert(Usuario usuario);

    @Update
    void update (Usuario usuario);

    @Delete
    void delete (Usuario usuario);

    @Query("SELECT * FROM usuario where usuario like :nick")
    Usuario findByUsuario(String nick);

    @Query("SELECT * FROM usuario where id = :id")
    Usuario findById(int id);
}
