package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Carta")
public class Carta {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre_duelista")
    public String nombreDuelista;

    public String nombre;
    @ColumnInfo(name = "puntos_ataque")
    public int puntosAtaque;
    @ColumnInfo(name = "puntos_defensa")
    public int puntosDefensa;
    public String imagen;
    public String ubicacionRegistro;

    public Carta(String nombre, int puntosAtaque, int puntosDefensa, String imagen, String ubicacionRegistro) {
        this.nombre = nombre;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.imagen = imagen;
        this.ubicacionRegistro = ubicacionRegistro;
    }
}
