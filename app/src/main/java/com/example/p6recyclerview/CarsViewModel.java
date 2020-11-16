package com.example.p6recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarsViewModel extends AndroidViewModel {
    CarsRepository carsRepository;
    public CarsViewModel(@NonNull Application application) {
        super(application);

        carsRepository = new CarsRepository(application);
    }
    LiveData<List<Car>> cars(){
        return carsRepository.cars();
    }

}
