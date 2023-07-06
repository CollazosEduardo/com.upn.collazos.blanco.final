package com.upn.collazos.blanco.finall;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.upn.collazos.blanco.finall.daos.CartaDao;
import com.upn.collazos.blanco.finall.daos.DuelistaDao;
import com.upn.collazos.blanco.finall.model.Duelista;

@Database(entities = {Duelista.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DuelistaDao duelistaDao();
    public abstract CartaDao cartaDao();
    private static volatile AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class,"FinalDatabase").allowMainThreadQueries().build();
        }
        return instance;
    }
}
