package com.bit.revalorando.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class Articulo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String descripcion;
    private String foto;
    private int categoria;
    private int condicion;
    private String estado;
    private int idUsuario;



    public Articulo() {

    }


}
