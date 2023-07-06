package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Duelista")
public class Duelista {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
}
