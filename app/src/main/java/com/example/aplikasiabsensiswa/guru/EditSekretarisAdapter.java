package com.example.aplikasiabsensiswa.guru;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Sekretaris;
import com.example.aplikasiabsensiswa.R;

import java.util.List;

class EditSekretarisViewHolder extends RecyclerView.ViewHolder {
    public TextView nis;
    public TextView nama;
    public TextView password;
    public TextView email;

    public EditSekretarisViewHolder(View itemView){
        super(itemView);
        nama = itemView.findViewById(R.id.textNama);
        nis = itemView.findViewById(R.id.textNis);
        password = itemView.findViewById(R.id.textPassword);
        email = itemView.findViewById(R.id.textEmail);
    }

}

public class EditSekretarisAdapter extends RecyclerView.Adapter<EditSekretarisViewHolder> {

    private List<Sekretaris> sekretarisList;

    public EditSekretarisAdapter(List<Sekretaris> sekretarisList){
        this.sekretarisList = sekretarisList;
    }

    @NonNull
    @Override
    public EditSekretarisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sekretaris_card, parent, false);
        return new EditSekretarisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditSekretarisViewHolder holder, int position) {
        Sekretaris sekretaris = sekretarisList.get(position);

        holder.nama.setText(sekretaris.nama);
        holder.email.setText(sekretaris.email);
        holder.password.setText(sekretaris.password);
        holder.nis.setText(sekretaris.nis);
    }

    @Override
    public int getItemCount() {
        return sekretarisList.size();
    }
}
