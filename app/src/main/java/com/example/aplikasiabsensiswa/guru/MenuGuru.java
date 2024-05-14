package com.example.aplikasiabsensiswa.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiabsensiswa.HalamanAwal;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.Riwayat;

public class MenuGuru extends AppCompatActivity {

    private TextView tvNama, tvNIP;
    private Button btnSekretaris, btnEditKelas, btnRiwayat;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIP = "nip";

    @SuppressLint({"SetTextI18n", "ApplySharedPref"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guru);

        tvNama = findViewById(R.id.tvNama);
        tvNIP = findViewById(R.id.tvNIP);
        btnSekretaris = findViewById(R.id.btnSekretaris);
        btnEditKelas = findViewById(R.id.btnKelas);
        btnRiwayat = findViewById(R.id.btnRiwayat);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String nama = sharedPreferences.getString(KEY_NAMA,null);
        String nip = sharedPreferences.getString(KEY_NIP,null);

        if (nama != null && nip != null){
            tvNama.setText(nama);
            tvNIP.setText(nip);
        }

        btnSekretaris.setOnClickListener(v -> {
            Intent sekretaris = new Intent(MenuGuru.this, EditSekretaris.class);
            startActivity(sekretaris);
        });
        btnEditKelas.setOnClickListener(v -> {
            Intent editkelas = new Intent(MenuGuru.this, EditKelas.class);
            startActivity(editkelas);
        });
        btnRiwayat.setOnClickListener(v -> {
            Intent riwayat = new Intent(MenuGuru.this, Riwayat.class);
            startActivity(riwayat);
        });


    }
}