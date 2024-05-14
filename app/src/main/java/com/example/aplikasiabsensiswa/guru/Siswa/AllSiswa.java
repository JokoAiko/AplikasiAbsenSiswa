package com.example.aplikasiabsensiswa.guru.Siswa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.Adapter.SiswaAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllSiswa extends AppCompatActivity {

    public List<Siswa> allSiswa;

    public DatabaseReference siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");



    RecyclerView recyclerView;

    SiswaAdapter siswaAdapter;


    public void getAllSiswa(Intent intent) {
        Query query = siswaRef.orderByChild("kelas_id").equalTo(intent.getStringExtra("kelasKey"));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allSiswa.clear();
                for (DataSnapshot siswaSnapshot : snapshot.getChildren()) {
                    Siswa siswa = siswaSnapshot.getValue(Siswa.class);
                    siswa.setKey(siswaSnapshot.getKey());
                    allSiswa.add(siswa);
                }
                siswaAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.activity_all_siswa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        allSiswa = new ArrayList<>();
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.siswaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        siswaAdapter = new SiswaAdapter(allSiswa, new SiswaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Siswa siswa) {
                Intent detailedIntent = new Intent(AllSiswa.this, DetailedSiswa.class);
                detailedIntent.putExtra("siswaNama", siswa.getNama());
                detailedIntent.putExtra("siswaNis", siswa.getNis());
                detailedIntent.putExtra("siswaAddress", siswa.getAddress());
                detailedIntent.putExtra("kelasKey", intent.getStringExtra("kelasKey"));
                detailedIntent.putExtra("siswaKey", siswa.getKey());
                detailedIntent.putExtra("siswaGender", siswa.getGender());
                startActivity(detailedIntent);
            }
        });
        recyclerView.setAdapter(siswaAdapter);
        getAllSiswa(intent);

        Button addButton = findViewById(R.id.addSiswa);
        addButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(AllSiswa.this, AddSiswa.class);
            intent1.putExtra("kelasKey", intent.getStringExtra("kelasKey"));
            startActivity(intent1);
        });

    }
}