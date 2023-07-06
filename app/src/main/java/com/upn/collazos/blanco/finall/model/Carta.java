package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Carta {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public int puntosAtaque;
    public int puntosDefensa;
    public String imagen;
    public String ubicacionRegistro;
}
