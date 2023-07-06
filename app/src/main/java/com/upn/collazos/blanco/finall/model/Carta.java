package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Carta")
public class Carta {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    @ColumnInfo(name = "puntos-ataque")
    public int puntosAtaque;
    @ColumnInfo(name = "puntos-defensa")
    public int puntosDefensa;
    public String imagen;
    public String ubicacionRegistro;
}
