package com.example.aplikasiabsensiswa.guru;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Sekretaris;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.Adapter.EditSekretarisAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditSekretaris extends AppCompatActivity {

    private List<Sekretaris> sekretarisList;

    private EditSekretarisAdapter adapter;

    private RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sekretaris);

        recyclerView = findViewById(R.id.kelasRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sekretarisList = new ArrayList<>();
        adapter = new EditSekretarisAdapter(sekretarisList);
        recyclerView.setAdapter(adapter);
        getSekretaris();

    }

    private void getSekretaris(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userSekretaris");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sekretarisList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Sekretaris sekretaris = dataSnapshot.getValue(Sekretaris.class);
                    sekretarisList.add(sekretaris);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}