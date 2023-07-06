package com.upn.collazos.blanco.finall.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Duelista")
public class Duelista {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public Duelista(String nombre) {
        this.nombre = nombre;
    }
}
