package com.ruthvikbr.medicinemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ruthvikbr.medicinemanager.data.Medicine;
import com.ruthvikbr.medicinemanager.data.MedicineViewModel;

public class CheckMedicineActivity extends AppCompatActivity {
    private MedicineViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine);
        viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
        final EditText checkDate = findViewById(R.id.checkDateET);
        final EditText checkTime = findViewById(R.id.checkTimeET);
        Button check = findViewById(R.id.checkButton);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Date = checkDate.getText().toString();
                String Time = checkTime.getText().toString();
                try {
                    Medicine m = viewModel.checkMedicine(Date, Time);
                    if (m != null) {
                        Toast.makeText(CheckMedicineActivity.this, "Take medicine " + m.getName() + " " + m.getTimeOfDay() + " on " + m.getDate(), Toast.LENGTH_LONG).show();
//                        Toast.makeText(getBaseContext(), "Alarm set in 1 seconds",Toast.LENGTH_LONG).show();
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                    } else {
                        Toast.makeText(CheckMedicineActivity.this, "No medicine at this time" + m, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CheckMedicineActivity.this, "Task Failed" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}