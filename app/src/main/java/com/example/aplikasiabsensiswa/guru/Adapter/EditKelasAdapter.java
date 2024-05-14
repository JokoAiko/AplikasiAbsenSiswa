package com.example.aplikasiabsensiswa.guru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Kelas;
import com.example.aplikasiabsensiswa.R;

import java.util.List;

class EditKelasViewHolder extends RecyclerView.ViewHolder{
    public TextView namaKelas;
    public TextView countSiswa;



    public EditKelasViewHolder(View itemView){
        super(itemView);
        namaKelas = itemView.findViewById(R.id.kelasText);
        countSiswa = itemView.findViewById(R.id.jumlahSiswa);
    }
}

public class EditKelasAdapter extends  RecyclerView.Adapter<EditKelasViewHolder>{

    private List<Kelas> allKelas;

    public interface OnItemClickListener {
        void onItemClick(Kelas kelas);
    }

    public final OnItemClickListener listener;

    public EditKelasAdapter(List<Kelas> allKelas, OnItemClickListener listener){
        this.listener = listener;
        this.allKelas = allKelas;
    }

    @NonNull
    @Override
    public EditKelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kelas_card, parent, false);
        return new EditKelasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditKelasViewHolder holder, int position) {
        Kelas kelas = allKelas.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(kelas);
            }
        });

        holder.namaKelas.setText(kelas.Kelas + " " + kelas.Jurusan);
        holder.countSiswa.setText("Siswa : " + kelas.getStudentCount());
    }

    @Override
    public int getItemCount() {
        return allKelas.size();
    }
}
