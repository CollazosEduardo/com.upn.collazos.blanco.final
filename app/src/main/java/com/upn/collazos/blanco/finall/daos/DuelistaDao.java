package com.upn.collazos.blanco.finall.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.List;

    @Dao
    public interface DuelistaDao {
        @Query("SELECT * FROM Duelista")
        List<Duelista> getAll();

        @Insert
        void insert(Duelista duelista);

        @Update
        void updateDuelista(Duelista duelista);
        @Query("SELECT * FROM Duelista WHERE nombre IN (:nombre)")
        Duelista loadAllByNombre(String nombre);
    }
