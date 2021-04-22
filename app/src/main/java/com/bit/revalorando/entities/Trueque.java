package com.bit.revalorando.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (indices = {@Index(value = {"idArticulo1"}, unique = true)})
public class Trueque {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String estado;
    private int idArticulo1;
    private int idArticulo2;
    private int idUsuario1;
    private int idUsuario2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdArticulo1() {
        return idArticulo1;
    }

    public void setIdArticulo1(int idArticulo1) {
        this.idArticulo1 = idArticulo1;
    }

    public int getIdArticulo2() {
        return idArticulo2;
    }

    public void setIdArticulo2(int idArticulo2) {
        this.idArticulo2 = idArticulo2;
    }

    public int getIdUsuario1() {
        return idUsuario1;
    }

    public void setIdUsuario1(int idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    public int getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(int idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public Trueque() {

    }

    public Trueque(int id, String estado, int idArticulo1, int idArticulo2, int idUsuario1, int idUsuario2) {
        this.id = id;
        this.estado = estado;
        this.idArticulo1 = idArticulo1;
        this.idArticulo2 = idArticulo2;
        this.idUsuario1 = idUsuario1;
        this.idUsuario2 = idUsuario2;
    }
}
