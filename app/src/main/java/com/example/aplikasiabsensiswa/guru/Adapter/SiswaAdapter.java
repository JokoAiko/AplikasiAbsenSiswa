package com.example.aplikasiabsensiswa.guru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;

import java.util.List;

class SiswaViewHolder extends RecyclerView.ViewHolder {
    public TextView nis;
    public TextView nama;
    public TextView address;
    public TextView gender;

    public SiswaViewHolder(View itemView) {
        super(itemView);
        nis = itemView.findViewById(R.id.textNISSiswa);
        nama = itemView.findViewById(R.id.textNamaSiswa);
        address = itemView.findViewById(R.id.textAddressSiswa);
        gender = itemView.findViewById(R.id.textKelaminSiswa);
    }
}

public class SiswaAdapter extends RecyclerView.Adapter<SiswaViewHolder> {
    private List<Siswa> siswaList;

    public interface OnItemClickListener {
        void onItemClick(Siswa siswa);
    }

    public final OnItemClickListener listener;

    public SiswaAdapter(List<Siswa> siswaList, OnItemClickListener listener) {
        this.siswaList = siswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.siswa_card, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(siswa);
            }});

        holder.nis.setText(siswa.getNis());
        holder.nama.setText(siswa.getNama());
        holder.address.setText(siswa.getAddress());
        holder.gender.setText(siswa.getGender());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }
}
