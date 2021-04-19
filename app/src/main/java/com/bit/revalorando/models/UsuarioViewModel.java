package com.bit.revalorando.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.repositories.UsuarioRepository;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;
    private final LiveData<List<Usuario>> usuarios;

    public UsuarioViewModel(Application application){
        super(application);
        usuarioRepository = new UsuarioRepository(application);
        usuarios = usuarioRepository.getUsuarios();
    }

    public LiveData<List<Usuario>> getUsuarios(){
        return usuarios;
    }

    public void insert(Usuario usuario){
        usuarioRepository.insert(usuario);
    }
}

