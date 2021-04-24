package com.bit.revalorando.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bit.revalorando.daos.UsuarioDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Usuario;

import java.util.List;

public class UsuarioRepository {
    private UsuarioDao usuarioDao;

    private LiveData<List<Usuario>> usuarios;

    public UsuarioRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        usuarioDao = db.usuarioDao();
        usuarios = usuarioDao.getAll();

    }

    public LiveData<List<Usuario>> getUsuarios(){
        return usuarios;
    }

    public void insert(Usuario usuario){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            usuarioDao.insert(usuario);
        });
    }

    public Usuario buscarUsuario(String nick){
        Usuario user = null;
        user = usuarioDao.findByUsuario(nick);

        return user;
    }
}
