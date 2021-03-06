package com.ruthvikbr.medicinemanager.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/*
Here we create a single database instance and return it. Hence we have used a null check
and "synchronized" before MedicineDatabase.class
*/

@Database(entities = {Medicine.class},version = 1,exportSchema = false)
public abstract class MedicineDatabase extends RoomDatabase {
    public static MedicineDatabase INSTANCE = null;
    public abstract MedicineDao medicineDao();

    public static  MedicineDatabase getInstance(final Context context){
        if(INSTANCE==null){
            synchronized (MedicineDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicineDatabase.class,
                            "TaskDatabase"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
