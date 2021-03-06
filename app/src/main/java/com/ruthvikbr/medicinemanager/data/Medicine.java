package com.ruthvikbr.medicinemanager.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Medicine")
public class Medicine {

    private String name;
    @PrimaryKey(autoGenerate = true)
    private long Id;
    private String timeOfDay;
    private String date;

    public Medicine(){}

    public Medicine(long id , String name, String timeOfDay, String date) {
        this.name = name;
        this.Id = id;
        this.timeOfDay = timeOfDay;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isMedicineItemEqual(Medicine t2){
        return ((name.equals(t2.getName())) && (date.equals(t2.getDate())) && (timeOfDay==t2.getTimeOfDay()));
    }
}
