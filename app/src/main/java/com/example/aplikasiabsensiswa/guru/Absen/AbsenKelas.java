package com.example.aplikasiabsensiswa.guru.Absen;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Kelas;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.Adapter.EditKelasAdapter;
import com.example.aplikasiabsensiswa.guru.DetailedKelas;
import com.example.aplikasiabsensiswa.guru.EditKelas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbsenKelas extends AppCompatActivity {

    RecyclerView kelasRecycler;

    private List<Kelas> allKelas;

    private EditKelasAdapter adapter;

    private void getKelas(){
        DatabaseReference kelasRef = FirebaseDatabase.getInstance().getReference("Kelas");
        DatabaseReference siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");
        kelasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allKelas.clear();
                for(DataSnapshot kelasData : snapshot.getChildren()){
                    Kelas kelas = kelasData.getValue(Kelas.class);
                    Query query = siswaRef.orderByChild("kelas_id").equalTo(kelasData.getKey());

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot siswaSnapshot) {
                            long count = siswaSnapshot.getChildrenCount();
                            kelas.setStudentCount(count);
                            kelas.setKelasKey(kelasData.getKey());
                            allKelas.add(kelas);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_absen_kelas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        kelasRecycler = findViewById(R.id.absenKelasRecyclerView);
        kelasRecycler.setHasFixedSize(true);
        kelasRecycler.setLayoutManager(new LinearLayoutManager(this));

        allKelas = new ArrayList<>();
        adapter = new EditKelasAdapter(allKelas, new EditKelasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Kelas kelas) {
                Intent intent = new Intent(AbsenKelas.this, AbsenSiswa.class);
                intent.putExtra("kelasKey",kelas.getKelasKey());
                startActivity(intent);
            }
        });

        kelasRecycler.setAdapter(adapter);
        getKelas();
    }
}