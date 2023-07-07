package com.upn.collazos.blanco.finall.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "Duelista")
public class Duelista {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    @ColumnInfo(name = "issinc")
    public boolean isSinc;

    public Duelista(String nombre) {
        this.nombre = nombre;
    }

    public void setSinc(boolean sinc) {
        this.isSinc = sinc;
    }

    public boolean isSinc() {
        return isSinc;
    }
}
