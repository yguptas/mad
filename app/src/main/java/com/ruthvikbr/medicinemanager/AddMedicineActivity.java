package com.ruthvikbr.medicinemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ruthvikbr.medicinemanager.data.Medicine;
import com.ruthvikbr.medicinemanager.data.MedicineViewModel;

public class AddMedicineActivity extends AppCompatActivity {
    private MedicineViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);

        final EditText name = findViewById(R.id.nameET);
        final EditText date = findViewById(R.id.DateET);
        final EditText time = findViewById(R.id.TimeET);
        Button add = findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Date = date.getText().toString();
                String Time = time.getText().toString();
                Medicine medicine = new Medicine(0L, Name, Date, Time);
                viewModel.insertMedicine(medicine);
                setResult(RESULT_OK);
                finish();
            }

        });
    }
}