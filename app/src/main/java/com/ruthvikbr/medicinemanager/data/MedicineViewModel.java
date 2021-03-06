package com.ruthvikbr.medicinemanager.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.concurrent.ExecutionException;

/*
ViewModel is a class that communicates with presentation layer (UI) and underlying data sources through repository.
*/
public class MedicineViewModel extends AndroidViewModel {

    private MedicineRepository repository;

    public LiveData<PagedList<Medicine>> medicinesList;

    public MedicineViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicineRepository(application);
        medicinesList = repository.getAllMedicines();
    }

    public void insertMedicine(Medicine medicine){
        repository.insertMedicine(medicine);
    }

    public void updateMedicine(Medicine medicine){
        repository.updateMedicine(medicine);
    }

    public void deleteMedicine(Medicine medicine){
        repository.deleteMedicine(medicine);
    }

    public Medicine checkMedicine(String date, String time) throws ExecutionException, InterruptedException {
        Medicine m = new Medicine();
        try {
            m = repository.getMedicine(date, time).get();
        }catch (Exception e){
            Log.v("log error",e.getMessage());
        }
       return m;
    }
}
