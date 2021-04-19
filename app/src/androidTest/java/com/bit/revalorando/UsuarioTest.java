package com.bit.revalorando;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bit.revalorando.daos.UsuarioDao;
import com.bit.revalorando.database.AppDatabase;
import com.bit.revalorando.entities.Usuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UsuarioTest {

    private UsuarioDao usuarioDao;
    private AppDatabase appDatabase;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        usuarioDao = appDatabase.usuarioDao();
    }

    @After
    public void closeDb() throws IOException {
        appDatabase.close();
    }

    @Test
    public void  findByNameTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Pedro");

        usuarioDao.insert(usuario);

        Usuario buscado = usuarioDao.findByNombre("Pedro");

        assertTrue("No encontro a Pedro", usuario.getId() == buscado.getId());
    }
}
