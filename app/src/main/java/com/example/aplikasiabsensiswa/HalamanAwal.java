package com.example.aplikasiabsensiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.aplikasiabsensiswa.guru.LoginGuru;
import com.example.aplikasiabsensiswa.guru.MenuGuru;
import com.example.aplikasiabsensiswa.sekretaris.LoginSekretaris;
import com.example.aplikasiabsensiswa.siswa.LoginSiswa;

public class HalamanAwal extends AppCompatActivity {


    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIP = "nip";
    private static final String KEY_PASSWORD = "pasword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_awal);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String nama = sharedPreferences.getString(KEY_NAMA,null);

        if (nama != null){
            Intent intent = new Intent(HalamanAwal.this, MenuGuru.class);
            startActivity(intent);
            finish();
        }

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