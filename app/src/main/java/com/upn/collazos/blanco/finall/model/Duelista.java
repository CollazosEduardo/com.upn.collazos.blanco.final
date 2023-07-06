package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Duelista {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
}
