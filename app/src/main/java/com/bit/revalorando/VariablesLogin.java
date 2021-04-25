package com.bit.revalorando;

import android.content.Context;

import androidx.room.Room;

import com.bit.revalorando.database.AppDatabase;


public final class VariablesLogin {
    private static VariablesLogin instance;

    public static int idUsuarioGlobal;
    public static String usuarioGlobal;
    public static String  passwordGlobal;
    public String busquedaGlobal;

    private VariablesLogin() {

        idUsuarioGlobal = -1;
        usuarioGlobal = "";
        passwordGlobal= "";
        busquedaGlobal = "";

    }

    public static VariablesLogin getInstance() {
        if (instance == null) {
            instance = new VariablesLogin();
        }
        return instance;
    }
}
