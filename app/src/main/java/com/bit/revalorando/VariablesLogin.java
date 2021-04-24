package com.bit.revalorando;

import android.content.Context;

import androidx.room.Room;

import com.bit.revalorando.database.AppDatabase;


public final class VariablesLogin {
    private static VariablesLogin instance;

    public static int idUsuarioGlobal;
    public static String usuarioGlobal;
    public static String  passwordGlobal;

    private VariablesLogin() {

        idUsuarioGlobal = -1;
        usuarioGlobal = "";
        passwordGlobal= "";
    }

    public static VariablesLogin getInstance() {
        if (instance == null) {
            instance = new VariablesLogin();
        }
        return instance;
    }
}
