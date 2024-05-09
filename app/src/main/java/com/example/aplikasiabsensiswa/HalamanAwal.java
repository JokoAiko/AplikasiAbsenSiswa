package com.example.aplikasiabsensiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.aplikasiabsensiswa.guru.LoginGuru;
import com.example.aplikasiabsensiswa.sekretaris.LoginSekretaris;
import com.example.aplikasiabsensiswa.siswa.LoginSiswa;

public class HalamanAwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_awal);

        Button btnGuru = findViewById(R.id.btnGuru);
        Button btnSekretaris = findViewById(R.id.btnSekretaris);
        Button btnSiswa = findViewById(R.id.btnSiswa);

        btnGuru.setOnClickListener(v -> {
            Intent intent1 = new Intent(HalamanAwal.this, LoginGuru.class);
            startActivity(intent1);
        });

        btnSekretaris.setOnClickListener(v -> {
            Intent intent2 = new Intent(HalamanAwal.this, LoginSekretaris.class);
            startActivity(intent2);
        });

        btnSiswa.setOnClickListener(v -> {
            Intent intent3 = new Intent(HalamanAwal.this, LoginSiswa.class);
            startActivity(intent3);
        });




    }
}