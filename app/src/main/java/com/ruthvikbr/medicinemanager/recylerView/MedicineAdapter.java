package com.ruthvikbr.medicinemanager.recylerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.ruthvikbr.medicinemanager.R;
import com.ruthvikbr.medicinemanager.data.Medicine;

public class MedicineAdapter extends PagedListAdapter<Medicine,ListViewHolder> {

    private static DiffUtil.ItemCallback<Medicine> DIFF_CALLBACK = new DiffUtil.ItemCallback<Medicine>() {
        @Override
        public boolean areItemsTheSame(@NonNull Medicine oldItem, @NonNull Medicine newItem) {
            return (oldItem.getName().equals(newItem.getName()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Medicine oldItem, @NonNull Medicine newItem) {
            return oldItem.isMedicineItemEqual(newItem);
        }
    };

    public MedicineAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Medicine currentItem = getItem(position);
        if (currentItem != null) {
            holder.bind(currentItem);
        }
    }
}
