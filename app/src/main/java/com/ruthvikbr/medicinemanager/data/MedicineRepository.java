package com.ruthvikbr.medicinemanager.data;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Repository in a class that interacts with room database and gives access to the database operations
All database operations have to be done in background thread . Hence Executors are used
*/
public class MedicineRepository {
    private static MedicineRepository repository = null;

    private MedicineDao medicineDao;

    private static int PAGE_SIZE = 15;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public MedicineRepository(Application application) {
        MedicineDatabase db = MedicineDatabase.getInstance(application);
        medicineDao = db.medicineDao();
    }

    public static MedicineRepository getRepository(Application application) {
        if (repository == null) {
            synchronized (MedicineRepository.class) {
                if (repository == null) {
                    repository = new MedicineRepository(application);
                }
            }
        }
        return repository;
    }

    public void insertMedicine(final Medicine medicine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                medicineDao.insertTask(medicine);
            }
        });
    }

    public void updateMedicine(final Medicine medicine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                medicineDao.updateTask(medicine);
            }
        });
    }

    public void deleteMedicine(final Medicine medicine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                medicineDao.deleteTask(medicine);
            }
        });
    }

    public LiveData<PagedList<Medicine>> getAllMedicines() {
        return new LivePagedListBuilder<>(
                medicineDao.getAllMedicines(), PAGE_SIZE
        ).build();
    }

    public Future<Medicine> getMedicine(String date, String time){
        Callable<Medicine> callable = new Callable<Medicine>() {
            @Override
            public Medicine call() {
                return  medicineDao.getMedicine(date,time);
            }
        };
    return executor.submit(callable);
    }

}