package com.bit.revalorando.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bit.revalorando.daos.ArticuloDao;
import com.bit.revalorando.daos.UsuarioDao;
import com.bit.revalorando.daos.TruequeDao;
import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.entities.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class, Articulo.class, Trueque.class}, version = 15)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    public abstract ArticuloDao articuloDao();
    public abstract TruequeDao truequeDao();


    private static volatile AppDatabase instance;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance( final Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "revalorando")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
