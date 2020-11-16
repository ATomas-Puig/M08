package com.example.p6recyclerview;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CarsRepository {
    CarDatabase.CarsDao dao;
    Executor executor = Executors.newSingleThreadExecutor();

    CarsRepository(Application application) {
        dao = CarDatabase.obtenerInstancia(application).obtenerCarsDao();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertar(new Car("Ferrari 250 GTO", "1968", R.drawable.f250gto));
                dao.insertar(new Car("Ferrari 250 GTyut", "1968", R.drawable.f250gto));
                dao.insertar(new Car("Ferrari 250yutu GTO", "1968", R.drawable.f250gto));
                dao.insertar(new Car("Ferrari 250 rteGTO", "1968", R.drawable.f250gto));
                dao.insertar(new Car("Fertewyrri 250 GTO", "1968", R.drawable.f250gto));
            }
        });


    }

    LiveData<List<Car>> cars() {
        return dao.obtener();
    }
}
