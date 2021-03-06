package com.ruthvikbr.medicinemanager.data;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

/*
Dao is an interface that contains all the database queries our app will be using
*/
@Dao
public interface MedicineDao {

    @Insert
    void insertTask(Medicine medicine);

    @Update
    void updateTask(Medicine medicine);

    @Delete
    void deleteTask(Medicine medicine);

    @Query("Select * from Medicine ")
    DataSource.Factory<Integer,Medicine> getAllMedicines();

    //SELECT * FROM Medicine WHERE date = :date AND timeOfDay = :time

    @Query("SELECT * FROM Medicine WHERE date = :date AND timeOfDay = :time ")
    Medicine getMedicine(String date,String time);

    @RawQuery(observedEntities = Medicine.class)
    Medicine getMedicine(SupportSQLiteQuery sqLiteQuery);
}
