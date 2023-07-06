package com.upn.collazos.blanco.finall.daos;

import androidx.room.Insert;
import androidx.room.Query;

import com.upn.collazos.blanco.finall.model.Carta;

import java.util.List;

public interface CartaDao {
    @Query("SELECT * FROM Carta")
    List<Carta> getAll();

    @Insert
    void insert(Carta carta);

    @Query("SELECT * FROM Carta WHERE nombre IN (:nombre)")
    Carta loadAllByNombre(String nombre);
}
