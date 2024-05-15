package com.example.aplikasiabsensiswa.guru.Absen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Absen;
import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.Adapter.AbsenSiswaAdapter;
import com.example.aplikasiabsensiswa.guru.Adapter.SiswaAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AbsenSiswa extends AppCompatActivity {
    public List<Siswa> allSiswa;

    public DatabaseReference siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");
    public DatabaseReference absenRef = FirebaseDatabase.getInstance().getReference("Absen");

    RecyclerView recyclerView;

    AbsenSiswaAdapter absenAdapter;

    public String getCheckedSiswa() {
        List<String> checkedSiswa = new ArrayList<>();
        for (Siswa siswa : allSiswa) {
            if (siswa.isChecked()) {
                checkedSiswa.add(siswa.getNama());
            }
        }
        return String.join(", ", checkedSiswa);
    }

    public void getAllSiswa(Intent intent) {
        Query query = siswaRef.orderByChild("kelas_id").equalTo(intent.getStringExtra("kelasKey"));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allSiswa.clear();
                for (DataSnapshot siswaSnapshot : snapshot.getChildren()) {
                    Siswa siswa = siswaSnapshot.getValue(Siswa.class);
                    Log.d("key", siswa.getNama());
                    allSiswa.add(siswa);
                }
                absenAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.activity_absen_siswa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.absenSiswaRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allSiswa = new ArrayList<>();
        absenAdapter = new AbsenSiswaAdapter(allSiswa);
        recyclerView.setAdapter(absenAdapter);
        Intent intent = getIntent();
        Log.d("key", intent.getStringExtra("kelasKey"));
        getAllSiswa(intent);

        Button button = findViewById(R.id.submitAbsenSiswaButton);
        EditText textJamKe = findViewById(R.id.textJamKe);
        EditText sampaiJamKe = findViewById(R.id.textSampaiJamKe);
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        String date = dtf.format(new java.util.Date());
        SharedPreferences sharedPreferences = getSharedPreferences("mypref",MODE_PRIVATE);
        String nip = sharedPreferences.getString("nip",null);

        button.setOnClickListener(v -> {
            Absen absen = new Absen(Integer.parseInt(textJamKe.getText().toString()), getIntent().getStringExtra("kelasKey"), nip, Integer.parseInt(sampaiJamKe.getText().toString()), getCheckedSiswa(), date);
            DatabaseReference newAbsenRef = absenRef.push();
            newAbsenRef.setValue(absen);
            finish();
        });
    }
}