package com.example.p6recyclerview;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Database(entities = {Car.class}, version = 1, exportSchema = false)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarsDao obtenerCarsDao();
    private static volatile CarDatabase INSTANCIA;

    static CarDatabase obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (CarDatabase.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            CarDatabase.class, "elementos.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface CarsDao {
        @Query("SELECT * FROM Car")
        LiveData<List<Car>> obtener();

        @Insert
        void insertar(Car elemento);

        @Update
        void actualizar(Car elemento);

        @Delete
        void eliminar(Car elemento);
    }
}