package com.ruthvikbr.medicinemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ruthvikbr.medicinemanager.data.Medicine;
import com.ruthvikbr.medicinemanager.data.MedicineViewModel;
import com.ruthvikbr.medicinemanager.recylerView.MedicineAdapter;

public class MainActivity extends AppCompatActivity {
    private MedicineViewModel viewModel;
    private FloatingActionButton addButton,checkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.medicineList);
        final MedicineAdapter medicineAdapter = new MedicineAdapter();
        recyclerView.setAdapter(medicineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        viewModel.medicinesList.observe(this, new Observer<PagedList<Medicine>>() {
                    @Override
                    public void onChanged(PagedList<Medicine> states) {
                        medicineAdapter.submitList(states);
                    }
                }
        );

        addButton = findViewById(R.id.addMedicine);
        checkButton = findViewById(R.id.checkMedicine);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddMedicineActivity.class);
                startActivity(intent);
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CheckMedicineActivity.class);
                startActivity(intent);
            }
        });
    }
}