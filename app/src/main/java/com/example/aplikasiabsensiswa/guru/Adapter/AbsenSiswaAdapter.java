package com.example.aplikasiabsensiswa.guru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;

import java.util.List;

class AbsenSiswaViewHolder extends RecyclerView.ViewHolder {
    public CheckBox nama;


    public AbsenSiswaViewHolder(View itemView) {
        super(itemView);
        nama = itemView.findViewById(R.id.absenCheckBox);
    }
}

public class AbsenSiswaAdapter extends RecyclerView.Adapter<AbsenSiswaViewHolder> {
    private List<Siswa> siswaList;




    public AbsenSiswaAdapter(List<Siswa> siswaList) {
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public AbsenSiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.absen_card, parent, false);
        return new AbsenSiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenSiswaViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        holder.nama.setOnCheckedChangeListener((buttonView, isChecked) -> siswa.setCheked(isChecked));
        holder.nama.setText(siswa.getNama());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }
}
