package com.upn.collazos.blanco.finall.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.List;


@Dao
public interface CartaDao {
    @Query("SELECT * FROM Carta")
    List<Carta> getAll();

    @Insert
    void insert(Carta carta);

    @Query("SELECT * FROM Carta WHERE nombre_duelista IN (:nombreDuelista)")
    List<Carta> loadAllCartsDuelista(String nombreDuelista);
    @Query("SELECT * FROM Carta WHERE nombre IN (:nombre)")
    Carta loadAllByNombre(String nombre);
}
