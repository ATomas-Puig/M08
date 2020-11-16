package com.example.p6recyclerview;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {
    @PrimaryKey(autoGenerate = true)
    int id;

    String nombre;
    String anyo;
    int imagen;


    public Car(String nombre, String anyo, int imagen) {
        this.nombre = nombre;
        this.anyo = anyo;
        this.imagen = imagen;
    }
}
