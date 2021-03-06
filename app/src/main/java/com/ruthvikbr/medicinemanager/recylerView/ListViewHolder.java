package com.ruthvikbr.medicinemanager.recylerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruthvikbr.medicinemanager.R;
import com.ruthvikbr.medicinemanager.data.Medicine;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTV, dateTV,timeTV;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById( R.id.nameTV);
        dateTV = itemView.findViewById( R.id.dateTv);
        timeTV = itemView.findViewById( R.id.timeTV);
    }

    public void bind(Medicine medicine) {
        nameTV.setText(medicine.getName());
        dateTV.setText(medicine.getDate());
        timeTV.setText(medicine.getTimeOfDay());
    }
}