package com.example.p6recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.p6recyclerview.databinding.FragmentCarsBinding;
import com.example.p6recyclerview.databinding.ViewholderCarsBinding;

import java.util.List;


public class CarsFragment extends Fragment {

    FragmentCarsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentCarsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CarsViewModel carsViewModel = new ViewModelProvider(this).get(CarsViewModel.class);

        CarsAdapter carsAdapter = new CarsAdapter();

        binding.recyclerView.setAdapter(carsAdapter);

        //binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        carsViewModel.cars().observe(getViewLifecycleOwner(), new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> carList) {
                carsAdapter.setCarList(carList);
            }
        });
    }

    class CarsAdapter extends RecyclerView.Adapter<CarsViewHolder>{

        List<Car> carList;

        @NonNull
        @Override
        public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CarsViewHolder(ViewholderCarsBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
            Car car = carList.get(position);

            holder.binding.nombre.setText(car.nombre);
            holder.binding.anyo.setText(car.anyo);
            Glide.with(CarsFragment.this).load(car.imagen).into(holder.binding.imagen);
        }

        @Override
        public int getItemCount() {
            return carList == null ? 0 : carList.size();
        }

        void setCarList(List<Car> carList){
            this.carList = carList;
            notifyDataSetChanged();
        }
    }

    class CarsViewHolder extends RecyclerView.ViewHolder{

        ViewholderCarsBinding binding;

        public CarsViewHolder(@NonNull ViewholderCarsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}